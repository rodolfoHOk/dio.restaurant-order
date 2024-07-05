package me.dio.hiokdev.restaurant_orders.delivery_service.api.dto;

import lombok.Builder;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.model.Delivery;

import java.time.OffsetDateTime;
import java.util.UUID;

public record DeliveryResponse(
        UUID id,
        UUID orderId,
        UUID customerId,
        String address,
        long shippingValue,
        OffsetDateTime createdAt
) {

    @Builder
    public DeliveryResponse {
    }

    public static DeliveryResponse fromDomain(Delivery delivery) {
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .orderId(delivery.getOrderId())
                .customerId(delivery.getCustomerId())
                .address(delivery.getAddress())
                .shippingValue(delivery.getShippingValue())
                .createdAt(delivery.getCreatedAt())
                .build();
    }

}
