package com.playstore.users.infrastructure.inputport;

import java.util.Optional;

import com.playstore.users.infrastructure.dto.UserRequestDTO;
import com.playstore.users.infrastructure.dto.UserRequestUpdateDTO;
import com.playstore.users.infrastructure.dto.UserResponseDTO;

public interface IUserInputport {

    UserResponseDTO save(UserRequestDTO user);

    UserResponseDTO update(Long id, UserRequestUpdateDTO user);

    Optional<UserResponseDTO> findById(Long id);

    String deleteById(Long id);
}