package com.playstore.games.infrastructure.outputPort;

import java.util.List;
import java.util.Optional;

import com.playstore.games.domain.Game;

public interface IGameMethod {

    public Game save(Game game);

    public void deleteById(Long id);

    public Optional<Game> findById(Long id);

    public List<Game> findAll(int page, int size);

}
