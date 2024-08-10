package com.playstore.games.infrastructure.outputAdapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playstore.games.domain.GameImage;

@Repository
public interface IGameImageRepository extends JpaRepository<GameImage, Long> {

}
