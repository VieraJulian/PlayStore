package com.playstore.games.application;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.playstore.games.application.exception.CategoryNotFoundException;
import com.playstore.games.application.exception.GameNotFoundException;
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

        BigDecimal finalPrice = game.getPrice();
        GameImage gameImage = null;
        GameImageDTO gameImageDTO = null;

        game.setCategory(game.getCategory().toUpperCase());
        ECategory gameCategory;

        try {
            gameCategory = ECategory.valueOf(game.getCategory());
        } catch (Exception e) {
            throw new CategoryNotFoundException("Category not found");
        }

        if (game.getDiscount() > 0) {
            BigDecimal price = game.getPrice();
            BigDecimal discount = BigDecimal.valueOf(game.getDiscount());
            BigDecimal discountAmount = price.multiply(discount).divide(BigDecimal.valueOf(100));
            BigDecimal discountedPrice = price.subtract(discountAmount);
            finalPrice = discountedPrice.setScale(2, RoundingMode.HALF_EVEN);
        }

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
                .release_date(LocalDate.now())
                .category(gameCategory)
                .enabled(true)
                .gameImage(gameImage)
                .build());

        if (gameImage != null) {
            gameImageDTO = GameImageDTO.builder()
                    .id(newGame.getGameImage().getId())
                    .image_url(newGame.getGameImage().getImage_url())
                    .build();
        }

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
    public GameResponseDTO updateGame(GameRequestDTO game, MultipartFile file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGame'");

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
