package me.dio.hiokdev.restaurant_orders.customer_api.domain.exception;

public class ResourceNotFoundException extends CustomerException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
