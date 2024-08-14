package com.playstore.users.infrastructure.dto;

import com.playstore.users.domain.Role;

import lombok.Builder;

@Builder
public record UserResponseDTO(
        Long id,
        String username,
        String email,
        boolean enabled,
        Role role) {
}