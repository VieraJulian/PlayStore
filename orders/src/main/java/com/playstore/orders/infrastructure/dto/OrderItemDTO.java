package com.playstore.orders.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {

    private Long id;
    private String title;
    private BigDecimal original_price;
    private BigDecimal final_price;
    private int discount;
    private int quantiy;
    private GameImageDTO image;
}
