package com.playstore.orders.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playstore.orders.infrastructure.dto.OrderDTO;
import com.playstore.orders.infrastructure.dto.OrderRequestDTO;
import com.playstore.orders.infrastructure.inputPort.IOrderInputPort;
import com.playstore.orders.infrastructure.outputPort.IGameServicePort;
import com.playstore.orders.infrastructure.outputPort.IOrderItemMethod;
import com.playstore.orders.infrastructure.outputPort.IOrderMethod;

@Service
public class OrderUseCase implements IOrderInputPort {

    @Autowired
    private IOrderMethod orderMethod;

    @Autowired
    private IOrderItemMethod orderItemMethod;

    @Autowired
    private IGameServicePort gameServ;

    @Override
    public OrderDTO findOrderById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOrderById'");
    }

    @Override
    public OrderDTO findOrderByCode(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOrderByCode'");
    }

    @Override
    public OrderDTO createOrder(OrderRequestDTO order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
    }

    @Override
    public String deleteOrderById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrderById'");
    }

}
