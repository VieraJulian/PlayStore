package com.playstore.games.application.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import com.playstore.games.application.exception.CategoryNotFoundException;
import com.playstore.games.domain.ECategory;
import com.playstore.games.domain.Game;
import com.playstore.games.domain.GameImage;
import com.playstore.games.infrastructure.dto.GameImageDTO;
import com.playstore.games.infrastructure.dto.GameResponseDTO;

public class GameUtils {

    public static GameImageDTO convertToGameImageDTO(GameImage gameImage) {
        return Optional.ofNullable(gameImage)
                .map(img -> GameImageDTO.builder()
                        .id(img.getId())
                        .image_url(img.getImage_url())
                        .build())
                .orElse(null);
    }

    public static GameResponseDTO convertToGameResponseDTO(Game game) {
        GameImageDTO gameImageDTO = (game.getGameImage() != null) ? GameImageDTO.builder()
                .id(game.getGameImage().getId())
                .image_url(game.getGameImage().getImage_url())
                .build() : null;

        return GameResponseDTO.builder().id(game.getId()).title(game.getTitle())
                .description(game.getDescription()).original_price(game.getOriginal_price())
                .final_price(game.getFinal_price()).discount(game.getDiscount())
                .release_date(game.getRelease_date()).category(game.getCategory().toString())
                .enabled(game.isEnabled()).image(gameImageDTO).build();

    }

    public static BigDecimal calculateFinalPrice(BigDecimal price, int discount) {

        if (discount > 0) {
            BigDecimal discountAmount = price.multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(100));
            return price.subtract(discountAmount).setScale(2, RoundingMode.HALF_EVEN);
        }

        return price;
    }

    public static ECategory setCategory(String category) throws CategoryNotFoundException {

        try {
            return ECategory.valueOf(category.toUpperCase());
        } catch (Exception e) {
            throw new CategoryNotFoundException("Category not found");
        }
    }
}
