package com.playstore.orders.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playstore.orders.infrastructure.outputPort.IOrderItemMethod;
import com.playstore.orders.infrastructure.outputPort.IOrderMethod;

@Service
public class OrderUseCase {

    @Autowired
    private IOrderMethod orderMethod;

    @Autowired
    private IOrderItemMethod orderItemMethod;
}
