package com.playstore.orders.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameRequestDTO {

    private Long product_id;
    private int quantity;
}
