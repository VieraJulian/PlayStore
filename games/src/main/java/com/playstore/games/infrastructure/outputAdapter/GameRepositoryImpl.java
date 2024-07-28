package com.playstore.games.infrastructure.outputAdapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.playstore.games.domain.Game;
import com.playstore.games.infrastructure.outputPort.IGameMethod;

@Component
public class GameRepositoryImpl implements IGameMethod {

    @Autowired
    private IGameRepository gameRepository;

    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

    @Override
    public Optional<Game> findById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Game> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return gameRepository.findAllGames(pageable);
    }
}
