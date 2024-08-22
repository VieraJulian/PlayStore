package com.playstore.orders.infrastructure.outputAdapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playstore.orders.domain.Order;
import com.playstore.orders.infrastructure.outputPort.IOrderMethod;

@Component
public class OrderRepositoryImpl implements IOrderMethod {

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
