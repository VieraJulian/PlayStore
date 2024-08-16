package com.playstore.users.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.playstore.users.application.exception.RoleNotFoundException;
import com.playstore.users.application.exception.UserNotFoundException;
import com.playstore.users.domain.Role;
import com.playstore.users.domain.UserEntity;
import com.playstore.users.infrastructure.dto.UserRequestDTO;
import com.playstore.users.infrastructure.dto.UserRequestUpdateDTO;
import com.playstore.users.infrastructure.dto.UserResponseDTO;
import com.playstore.users.infrastructure.inputport.IUserInputport;
import com.playstore.users.infrastructure.outputPort.IRoleMethods;
import com.playstore.users.infrastructure.outputPort.IUserMethods;

@Service
public class UserUseCase implements IUserInputport {

    @Autowired
    private IUserMethods userMethods;

    @Autowired
    private IRoleMethods roleMethods;

    @Override
    public UserResponseDTO save(UserRequestDTO user) throws RoleNotFoundException {

        Optional<UserEntity> username = userMethods.findUserEntityByUsername(user.getUsername());

        if (username.isPresent()) {
            return null;
        }

        Role role = roleMethods.findById(user.getRole().getId());

        if (role != null) {

            UserEntity userInfo = UserEntity.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .enabled(true)
                    .role(role)
                    .build();

            UserEntity newUser = userMethods.save(userInfo);

            return UserResponseDTO.builder()
                    .id(newUser.getId())
                    .username(newUser.getUsername())
                    .email(newUser.getEmail())
                    .enabled(newUser.isEnabled())
                    .role(newUser.getRole())
                    .build();
        }

        return null;

    }

    @Override
    public UserResponseDTO update(Long id, UserRequestUpdateDTO user) throws UserNotFoundException {
        UserEntity userDB = userMethods.findById(id);

        if (userDB == null) {
            return null;
        }

        userDB.setEmail(user.email());

        if (!user.username().isEmpty() && userMethods.findUserEntityByUsername(user.username()).isEmpty()) {
            userDB.setUsername(user.username());
        }

        UserEntity userUpdated = userMethods.save(userDB);

        return UserResponseDTO.builder()
                .id(userUpdated.getId())
                .username(userUpdated.getUsername())
                .email(userUpdated.getEmail())
                .enabled(userUpdated.isEnabled())
                .role(userUpdated.getRole())
                .build();

    }

    @Override
    public UserResponseDTO findById(Long id) throws UserNotFoundException {
        UserEntity userDB = userMethods.findById(id);

        return UserResponseDTO.builder()
                .id(userDB.getId())
                .username(userDB.getUsername())
                .email(userDB.getEmail())
                .enabled(userDB.isEnabled())
                .role(userDB.getRole())
                .build();
    }

    @Override
    public String deleteById(Long id) {
        userMethods.deleteById(id);

        return "User deleted successfully.";
    }
}
