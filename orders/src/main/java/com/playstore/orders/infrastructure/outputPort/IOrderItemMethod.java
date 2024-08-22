package com.playstore.orders.infrastructure.outputPort;

import java.util.Optional;

import com.playstore.orders.domain.OrderItem;

public interface IOrderItemMethod {

    OrderItem save(OrderItem orderItem);

    Optional<OrderItem> findById(Long id);

    void deleteById(Long id);
}
