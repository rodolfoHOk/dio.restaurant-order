package me.dio.hiokdev.restaurant_orders.delivery_service.domain.exception;

public class BadRequestException extends DeliveryException {

    public BadRequestException(String message) {
        super(message);
    }

}
