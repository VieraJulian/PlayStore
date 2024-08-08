package com.playstore.games.application;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.playstore.games.application.exception.CategoryNotFoundException;
import com.playstore.games.application.exception.GameNotFoundException;
import com.playstore.games.application.utils.CalculateFinalPrice;
import com.playstore.games.application.utils.CategoryUtils;
import com.playstore.games.domain.ECategory;
import com.playstore.games.domain.Game;
import com.playstore.games.domain.GameImage;
import com.playstore.games.infrastructure.dto.GameImageDTO;
import com.playstore.games.infrastructure.dto.GameRequestDTO;
import com.playstore.games.infrastructure.dto.GameResponseDTO;
import com.playstore.games.infrastructure.inputPort.IGameInputPort;
import com.playstore.games.infrastructure.outputPort.IGameMethod;

@Service
public class GameUseCase implements IGameInputPort {

    @Autowired
    private IGameMethod gameMethod;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public GameResponseDTO createGame(GameRequestDTO game, MultipartFile file)
            throws CategoryNotFoundException, IOException {

        BigDecimal finalPrice = CalculateFinalPrice.calculateFinalPrice(game.getPrice(), game.getDiscount());
        ECategory gameCategory = CategoryUtils.setCategory(game.getCategory());
        GameImage gameImage = null;

        if (file != null && file.isEmpty() != true) {

            if (file.getSize() > 5000000) {
                throw new IllegalArgumentException("The image exceeds 5 MB");
            }

            String originalFilename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFilename);

            if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                throw new IllegalArgumentException("The allowed extensions are ‘.jpg’, ‘.jpe’, ‘.png’");
            }

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = uploadResult.get("url").toString();

            gameImage = GameImage.builder()
                    .image_url(url)
                    .build();
        }

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

        GameImageDTO gameImageDTO = (gameImage != null) ? GameImageDTO.builder()
                .id(newGame.getGameImage().getId())
                .image_url(newGame.getGameImage().getImage_url())
                .build() : null;

        return GameResponseDTO.builder()
                .id(newGame.getId())
                .title(newGame.getTitle())
                .description(newGame.getDescription())
                .original_price(newGame.getOriginal_price())
                .final_price(newGame.getFinal_price())
                .discount(newGame.getDiscount())
                .release_date(newGame.getRelease_date())
                .category(newGame.getCategory().toString())
                .enabled(newGame.isEnabled())
                .image(gameImageDTO)
                .build();
    }

    @Override
    public GameResponseDTO updateGame(Long id, GameRequestDTO game, MultipartFile file)
            throws GameNotFoundException, CategoryNotFoundException, IOException {
        Game gameDB = gameMethod.findById(id);

        BigDecimal finalPrice = CalculateFinalPrice.calculateFinalPrice(game.getPrice(), game.getDiscount());
        ECategory eCategory = CategoryUtils.setCategory(game.getCategory());
        GameImage gameImage = null;

        if (file != null && file.isEmpty() != true) {

            if (file.getSize() > 5000000) {
                throw new IllegalArgumentException("The image exceeds 5 MB");
            }

            String originalFilename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFilename);

            if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
                throw new IllegalArgumentException("The allowed extensions are ‘.jpg’, ‘.jpe’, ‘.png’");
            }

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = uploadResult.get("url").toString();

            gameImage = GameImage.builder()
                    .image_url(url)
                    .build();
        }

        gameDB.setTitle(game.getTitle());
        gameDB.setDescription(game.getDescription());
        gameDB.setOriginal_price(game.getPrice());
        gameDB.setFinal_price(finalPrice);
        gameDB.setDiscount(game.getDiscount());
        gameDB.setRelease_date(LocalDate.parse(game.getRelease_date()));
        gameDB.setCategory(eCategory);
        if (gameImage != null) {
            gameDB.setGameImage(gameImage);
        }

        Game gameUpdated = gameMethod.save(gameDB);

        GameImageDTO gameImageDTO = (gameImage != null) ? GameImageDTO.builder()
                .id(gameUpdated.getGameImage().getId())
                .image_url(gameUpdated.getGameImage().getImage_url())
                .build() : null;

        return GameResponseDTO.builder()
                .id(gameUpdated.getId())
                .title(gameUpdated.getTitle())
                .description(gameUpdated.getDescription())
                .original_price(gameUpdated.getOriginal_price())
                .final_price(gameUpdated.getFinal_price())
                .discount(gameUpdated.getDiscount())
                .release_date(gameUpdated.getRelease_date())
                .category(gameUpdated.getCategory().toString())
                .enabled(gameUpdated.isEnabled())
                .image(gameImageDTO)
                .build();

    }

    @Override
    public GameResponseDTO findGameById(Long id) throws GameNotFoundException {
        Game game = gameMethod.findById(id);
        GameImageDTO gameImageDTO = null;

        if (game.getGameImage() != null) {
            gameImageDTO = GameImageDTO.builder()
                    .id(game.getGameImage().getId())
                    .image_url(game.getGameImage().getImage_url())
                    .build();
        }

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
                .image(gameImageDTO)
                .build();
    }

    @Override
    public List<GameResponseDTO> findAllGames(int page, int size) {
        List<Game> gamesListDB = gameMethod.findAll(page, size);
        List<GameResponseDTO> gamesList = new ArrayList<>();

        gamesListDB.forEach((game) -> {
            GameImageDTO gameImageDTO = null;

            if (game.getGameImage() != null) {
                gameImageDTO = GameImageDTO.builder()
                        .id(game.getGameImage().getId())
                        .image_url(game.getGameImage().getImage_url())
                        .build();
            }

            gamesList.add(GameResponseDTO.builder()
                    .id(game.getId())
                    .title(game.getTitle())
                    .description(game.getDescription())
                    .original_price(game.getOriginal_price())
                    .final_price(game.getFinal_price())
                    .discount(game.getDiscount())
                    .release_date(game.getRelease_date())
                    .category(game.getCategory().toString())
                    .enabled(game.isEnabled())
                    .image(gameImageDTO)
                    .build());
        });

        return gamesList;

    }

    @Override
    public String deleteGameById(Long id) {
        gameMethod.deleteById(id);

        return "Game deleted successfully";
    }

}
