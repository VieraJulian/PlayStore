package com.playstore.games.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.playstore.games.application.exception.GameNotFoundException;
import com.playstore.games.domain.Game;
import com.playstore.games.infrastructure.dto.GameImageDTO;
import com.playstore.games.infrastructure.dto.GameRequestDTO;
import com.playstore.games.infrastructure.dto.GameResponseDTO;
import com.playstore.games.infrastructure.inputPort.IGameInputPort;
import com.playstore.games.infrastructure.outputPort.IGameMethod;

@Service
public class GameUseCase implements IGameInputPort {

    @Autowired
    private IGameMethod gameMethod;

    @Override
    public GameResponseDTO createGame(GameRequestDTO game, MultipartFile file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createGame'");
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
                .discount_price(game.getDiscount_price())
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
                    .discount_price(game.getDiscount_price())
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
