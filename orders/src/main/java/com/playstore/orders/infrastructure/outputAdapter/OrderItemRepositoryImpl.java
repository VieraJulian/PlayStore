package com.playstore.orders.infrastructure.outputAdapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playstore.orders.domain.OrderItem;
import com.playstore.orders.infrastructure.outputPort.IOrderItemMethod;

@Component
public class OrderItemRepositoryImpl implements IOrderItemMethod {

    @Autowired
    private IOrderItemRepository orderItemRepository;

    @Override
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
