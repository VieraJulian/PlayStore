package com.playstore.orders.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private Long id;
    private Long user_id;
    private BigDecimal final_price;
    private LocalDate date_purchase;
    private String code_operation;
    private boolean enabled;
    private List<OrderItemDTO> ordersItems;

}
