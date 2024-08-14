package com.playstore.users.infrastructure.dto;

public record UserRequestDTO(
        String username,
        String email,
        String password) {

}
