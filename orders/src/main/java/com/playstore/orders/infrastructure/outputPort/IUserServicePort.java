package com.playstore.orders.infrastructure.outputPort;

import com.playstore.orders.infrastructure.dto.UserDTO;

public interface IUserServicePort {

    UserDTO getUser(Long id);
}
