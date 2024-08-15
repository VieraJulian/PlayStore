package com.playstore.users.infrastructure.dto;

import com.playstore.users.domain.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequestDTO {
        String username;
        String email;
        String password;
        Role role;

}
