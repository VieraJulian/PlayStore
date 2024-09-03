package com.playstore.orders.infrastructure.outputAdapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.playstore.orders.infrastructure.dto.UserDTO;
import com.playstore.orders.infrastructure.outputPort.IUserServicePort;

@FeignClient(name = "users")
public interface IUserServiceAdapter extends IUserServicePort {

    @Override
    @GetMapping("/users/{id}")
    UserDTO getUser(@PathVariable Long id);

}
