package me.dio.hiokdev.restaurant_orders.order_service.domain.exception;

public class ResourceNotFoundException extends OrderException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
