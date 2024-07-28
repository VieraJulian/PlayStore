package com.playstore.games.infrastructure.outputAdapter;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.playstore.games.domain.Game;

@Repository
public interface IGameRepository extends JpaRepository<Game, Long> {

    @Query(value = "SELECT g FROM Game g")
    public List<Game> findAllGames(Pageable pageable);
}
