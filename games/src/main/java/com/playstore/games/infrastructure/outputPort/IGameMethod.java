package com.playstore.games.infrastructure.outputPort;

import java.util.List;

import com.playstore.games.application.exception.GameNotFoundException;
import com.playstore.games.domain.Game;

public interface IGameMethod {

    public Game save(Game game);

    public void deleteById(Long id);

    public Game findById(Long id) throws GameNotFoundException;

    public List<Game> findAll(int page, int size);

}
