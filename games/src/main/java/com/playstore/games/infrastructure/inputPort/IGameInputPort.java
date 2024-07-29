package com.playstore.games.infrastructure.inputPort;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.playstore.games.infrastructure.dto.GameRequestDTO;
import com.playstore.games.infrastructure.dto.GameResponseDTO;

public interface IGameInputPort {

    GameResponseDTO createGame(GameRequestDTO game, MultipartFile file);

    GameResponseDTO updateGame(GameRequestDTO game, MultipartFile file);

    GameResponseDTO findGameById(Long id);

    List<GameResponseDTO> findAllGames(int page, int size);

    void deleteGameById(Long id);

}
