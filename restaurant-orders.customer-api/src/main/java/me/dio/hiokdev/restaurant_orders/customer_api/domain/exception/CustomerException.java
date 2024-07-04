package me.dio.hiokdev.restaurant_orders.customer_api.domain.exception;

public class CustomerException extends RuntimeException {
    public CustomerException(String message) {
        super(message);
    }
}
