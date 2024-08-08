package com.playstore.games.infrastructure.inputPort;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.playstore.games.application.exception.CategoryNotFoundException;
import com.playstore.games.application.exception.GameNotFoundException;
import com.playstore.games.infrastructure.dto.GameRequestDTO;
import com.playstore.games.infrastructure.dto.GameResponseDTO;

public interface IGameInputPort {

    GameResponseDTO createGame(GameRequestDTO game, MultipartFile file) throws CategoryNotFoundException, IOException;

    GameResponseDTO updateGame(Long id, GameRequestDTO game, MultipartFile file)
            throws GameNotFoundException, CategoryNotFoundException, IOException;

    GameResponseDTO findGameById(Long id) throws GameNotFoundException;

    List<GameResponseDTO> findAllGames(int page, int size);

    String deleteGameById(Long id);

}
