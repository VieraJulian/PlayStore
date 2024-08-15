package com.playstore.users.infrastructure.outputPort;

import java.util.Optional;

import com.playstore.users.application.exception.UserNotFoundException;
import com.playstore.users.domain.UserEntity;

public interface IUserMethods {

    UserEntity save(UserEntity user);

    UserEntity findById(Long id) throws UserNotFoundException;

    Optional<UserEntity> findUserEntityByUsername(String username);

    void deleteById(Long id);

}
