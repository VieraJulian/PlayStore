package com.playstore.orders.infrastructure.inputAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.playstore.orders.infrastructure.dto.OrderDTO;
import com.playstore.orders.infrastructure.dto.OrderRequestDTO;
import com.playstore.orders.infrastructure.inputPort.IOrderInputPort;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderInputPort orderInputPort;

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderRequestDTO orderReq) {
        try {
            OrderDTO order = orderInputPort.createOrder(orderReq);
            return new ResponseEntity<>(order, HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error("Error creating order", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        try {
            OrderDTO order = orderInputPort.findOrderById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error getting order", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
