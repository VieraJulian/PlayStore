package com.playstore.games.application;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.playstore.games.application.exception.CategoryNotFoundException;
import com.playstore.games.application.exception.GameNotFoundException;
import com.playstore.games.application.utils.GameUtils;
import com.playstore.games.application.utils.ImageUtils;
import com.playstore.games.domain.ECategory;
import com.playstore.games.domain.Game;
import com.playstore.games.domain.GameImage;
import com.playstore.games.infrastructure.dto.GameRequestDTO;
import com.playstore.games.infrastructure.dto.GameResponseDTO;
import com.playstore.games.infrastructure.inputPort.IGameInputPort;
import com.playstore.games.infrastructure.outputPort.IGameImageMethod;
import com.playstore.games.infrastructure.outputPort.IGameMethod;

@Service
public class GameUseCase implements IGameInputPort {

    @Autowired
    private IGameMethod gameMethod;

    @Autowired
    private IGameImageMethod gameImageMethod;

    @Autowired
    private ImageUtils imageUtils;

    @Override
    public GameResponseDTO createGame(GameRequestDTO game, MultipartFile file)
            throws CategoryNotFoundException, IOException {

        BigDecimal finalPrice = GameUtils.calculateFinalPrice(game.getPrice(), game.getDiscount());
        ECategory gameCategory = GameUtils.setCategory(game.getCategory());
        GameImage gameImage = imageUtils.fileUpload(file);

        Game newGame = gameMethod.save(Game.builder()
                .title(game.getTitle())
                .description(game.getDescription())
                .original_price(game.getPrice())
                .final_price(finalPrice)
                .discount(game.getDiscount())
                .release_date(LocalDate.parse(game.getRelease_date()))
                .category(gameCategory)
                .enabled(true)
                .gameImage(gameImage)
                .build());

        return GameUtils.convertToGameResponseDTO(newGame);

    }

    @Override
    public GameResponseDTO updateGame(Long id, GameRequestDTO game, MultipartFile file)
            throws GameNotFoundException, CategoryNotFoundException, IOException {
        Game gameDB = gameMethod.findById(id);
        Long imgId = (gameDB.getGameImage() != null) ? gameDB.getGameImage().getId() : null;

        BigDecimal finalPrice = GameUtils.calculateFinalPrice(game.getPrice(), game.getDiscount());
        ECategory eCategory = GameUtils.setCategory(game.getCategory());
        GameImage gameImage = imageUtils.fileUpload(file);

        gameDB.setTitle(game.getTitle());
        gameDB.setDescription(game.getDescription());
        gameDB.setOriginal_price(game.getPrice());
        gameDB.setFinal_price(finalPrice);
        gameDB.setDiscount(game.getDiscount());
        gameDB.setRelease_date(LocalDate.parse(game.getRelease_date()));
        gameDB.setCategory(eCategory);
        if (gameImage != null) {
            gameDB.setGameImage(gameImage);
            if (imgId != null) {
                gameImageMethod.deleteById(imgId);
            }
        }

        Game gameUpdated = gameMethod.save(gameDB);

        return GameUtils.convertToGameResponseDTO(gameUpdated);

    }

    @Override
    public GameResponseDTO findGameById(Long id) throws GameNotFoundException {
        Game game = gameMethod.findById(id);

        return GameResponseDTO.builder()
                .id(game.getId())
                .title(game.getTitle())
                .description(game.getDescription())
                .original_price(game.getOriginal_price())
                .final_price(game.getFinal_price())
                .discount(game.getDiscount())
                .release_date(game.getRelease_date())
                .category(game.getCategory().toString())
                .enabled(game.isEnabled())
                .image(GameUtils.convertToGameImageDTO(game.getGameImage()))
                .build();
    }

    @Override
    public List<GameResponseDTO> findAllGames(int page, int size) {
        return gameMethod.findAll(page, size).stream()
                .map(game -> {

                    return GameResponseDTO.builder()
                            .id(game.getId())
                            .title(game.getTitle())
                            .description(game.getDescription())
                            .original_price(game.getOriginal_price())
                            .final_price(game.getFinal_price())
                            .discount(game.getDiscount())
                            .release_date(game.getRelease_date())
                            .category(game.getCategory().toString())
                            .enabled(game.isEnabled())
                            .image(GameUtils.convertToGameImageDTO(game.getGameImage()))
                            .build();

                }).collect(Collectors.toList());
    }

    @Override
    public String deleteGameById(Long id) {
        gameMethod.deleteById(id);

        return "Game deleted successfully";
    }

}
