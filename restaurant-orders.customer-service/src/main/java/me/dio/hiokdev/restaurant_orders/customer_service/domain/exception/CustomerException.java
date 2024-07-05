package me.dio.hiokdev.restaurant_orders.customer_service.domain.exception;

public class CustomerException extends RuntimeException {
    public CustomerException(String message) {
        super(message);
    }
}
