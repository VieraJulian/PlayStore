package com.playstore.orders.application.exception;

public class OrderNotFoundException extends Exception {

    public OrderNotFoundException(String message) {
        super(message);
    }

}
