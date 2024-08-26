package com.playstore.orders.infrastructure.inputPort;

import com.playstore.orders.infrastructure.dto.OrderDTO;
import com.playstore.orders.infrastructure.dto.OrderRequestDTO;

public interface IOrderInputPort {

    OrderDTO findOrderById(Long id);

    OrderDTO findOrderByCode(String code);

    OrderDTO createOrder(OrderRequestDTO order);

    String deleteOrderById(Long id);
}
