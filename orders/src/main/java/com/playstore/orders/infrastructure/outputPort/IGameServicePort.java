package com.playstore.orders.infrastructure.outputPort;

import com.playstore.orders.infrastructure.dto.GameDTO;

public interface IGameServicePort {

    public GameDTO getGame(Long id);
}
