package com.playstore.users.infrastructure.dto;

import com.playstore.users.domain.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponseDTO {

        Long id;
        String username;
        String email;
        boolean enabled;
        Role role;
}