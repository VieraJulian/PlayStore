package com.playstore.users.infrastructure.outputPort;

import java.util.Optional;

import com.playstore.users.domain.UserEntity;

public interface IUserMethods {

    UserEntity save(UserEntity user);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findUserEntityByUsername(String username);

    void deleteById(Long id);

}
