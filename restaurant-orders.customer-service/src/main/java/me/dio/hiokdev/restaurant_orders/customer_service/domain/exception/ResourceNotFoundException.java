package me.dio.hiokdev.restaurant_orders.customer_service.domain.exception;

public class ResourceNotFoundException extends CustomerException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
