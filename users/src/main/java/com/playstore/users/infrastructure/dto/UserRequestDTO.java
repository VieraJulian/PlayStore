package com.playstore.users.infrastructure.dto;

import com.playstore.users.domain.Role;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
        String username;
        String email;
        String password;
        Role role;
}
