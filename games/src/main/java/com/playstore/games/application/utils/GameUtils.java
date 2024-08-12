package com.playstore.games.application.utils;

import java.util.Optional;

import com.playstore.games.domain.GameImage;
import com.playstore.games.infrastructure.dto.GameImageDTO;

public class GameUtils {

    public static GameImageDTO convertToGameImageDTO(GameImage gameImage) {
        return Optional.ofNullable(gameImage)
                .map(img -> GameImageDTO.builder()
                        .id(img.getId())
                        .image_url(img.getImage_url())
                        .build())
                .orElse(null);
    }
}
