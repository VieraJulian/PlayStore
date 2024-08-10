package com.playstore.games.infrastructure.outputAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playstore.games.infrastructure.outputPort.IGameImageMethod;

@Component
public class GameImageRepositoryImpl implements IGameImageMethod {

    @Autowired
    private IGameImageRepository gameImageRepository;

    @Override
    public void deleteById(Long id) {
        gameImageRepository.deleteById(id);
    }

}
