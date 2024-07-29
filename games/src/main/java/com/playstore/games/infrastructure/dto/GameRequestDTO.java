package com.playstore.games.infrastructure.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameRequestDTO {

    private String title;
    private String description;
    private BigDecimal price;
    private int discount;
    private String category;
}