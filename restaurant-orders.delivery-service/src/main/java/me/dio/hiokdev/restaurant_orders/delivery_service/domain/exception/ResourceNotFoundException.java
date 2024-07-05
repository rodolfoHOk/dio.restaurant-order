package me.dio.hiokdev.restaurant_orders.delivery_service.domain.exception;

public class ResourceNotFoundException extends DeliveryException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
