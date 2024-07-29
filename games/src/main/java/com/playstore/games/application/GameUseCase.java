package com.playstore.games.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public GameResponseDTO findGameById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findGameById'");
    }

    @Override
    public List<GameResponseDTO> findAllGames(int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllGames'");
    }

    @Override
    public void deleteGameById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteGameById'");
    }

}
