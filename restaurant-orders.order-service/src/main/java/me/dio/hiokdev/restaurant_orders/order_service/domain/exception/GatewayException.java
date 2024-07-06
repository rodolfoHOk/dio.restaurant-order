package me.dio.hiokdev.restaurant_orders.order_service.domain.exception;

public class GatewayException extends OrderException {

    public GatewayException(String message) {
        super(message);
    }

}
