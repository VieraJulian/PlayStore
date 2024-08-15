package com.playstore.users.infrastructure.inputport;

import com.playstore.users.application.exception.RoleNotFoundException;
import com.playstore.users.application.exception.UserNotFoundException;
import com.playstore.users.infrastructure.dto.UserRequestDTO;
import com.playstore.users.infrastructure.dto.UserRequestUpdateDTO;
import com.playstore.users.infrastructure.dto.UserResponseDTO;

public interface IUserInputport {

    UserResponseDTO save(UserRequestDTO user) throws RoleNotFoundException;

    UserResponseDTO update(Long id, UserRequestUpdateDTO user);

    UserResponseDTO findById(Long id) throws UserNotFoundException;

    String deleteById(Long id);
}