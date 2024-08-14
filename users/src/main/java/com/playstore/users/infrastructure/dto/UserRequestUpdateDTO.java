package com.playstore.users.infrastructure.dto;

import lombok.Builder;

@Builder
public record UserRequestUpdateDTO(String username, String email) {
}