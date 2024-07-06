package me.dio.hiokdev.restaurant_orders.order_service.domain.exception;

public class BadRequestException extends OrderException {

    public BadRequestException(String message) {
        super(message);
    }

}
