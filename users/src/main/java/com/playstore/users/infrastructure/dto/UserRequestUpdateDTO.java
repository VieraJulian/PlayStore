package com.playstore.users.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserRequestUpdateDTO {
    String username;
    String email;
}