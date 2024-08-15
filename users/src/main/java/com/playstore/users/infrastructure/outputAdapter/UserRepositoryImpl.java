package com.playstore.users.infrastructure.outputAdapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playstore.users.application.exception.UserNotFoundException;
import com.playstore.users.domain.UserEntity;
import com.playstore.users.infrastructure.outputPort.IUserMethods;

@Component
public class UserRepositoryImpl implements IUserMethods {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @Override
    public Optional<UserEntity> findUserEntityByUsername(String username) {
        return userRepository.findUserEntityByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
