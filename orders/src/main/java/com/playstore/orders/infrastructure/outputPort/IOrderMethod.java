package com.playstore.orders.infrastructure.outputPort;

import java.util.Optional;

import com.playstore.orders.application.exception.OrderNotFoundException;
import com.playstore.orders.domain.Order;

public interface IOrderMethod {

    Order save(Order order);

    Optional<Order> findById(Long id);

    void deleteById(Long id);

    Optional<Order> findByCodeOperation(String codeOp);
}
