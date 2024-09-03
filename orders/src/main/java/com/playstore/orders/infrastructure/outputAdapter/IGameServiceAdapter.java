package com.playstore.orders.infrastructure.outputAdapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.playstore.orders.infrastructure.dto.GameDTO;
import com.playstore.orders.infrastructure.outputPort.IGameServicePort;

@FeignClient(name = "games")
public interface IGameServiceAdapter extends IGameServicePort {

    @Override
    @GetMapping("/games/{id}")
    public GameDTO getGame(@PathVariable Long id);

}
