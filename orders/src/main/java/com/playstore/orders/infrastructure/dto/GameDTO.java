package com.playstore.orders.infrastructure.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

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
public class GameDTO {

    private Long id;
    private String title;
    private String description;
    private BigDecimal original_price;
    private BigDecimal final_price;
    private int discount;
    private LocalDate release_date;
    private String category;
    private GameImage gameImage;
}