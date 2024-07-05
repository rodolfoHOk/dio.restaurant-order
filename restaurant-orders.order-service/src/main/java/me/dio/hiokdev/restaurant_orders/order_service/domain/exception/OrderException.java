package me.dio.hiokdev.restaurant_orders.order_service.domain.exception;

public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
}
