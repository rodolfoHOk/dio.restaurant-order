package me.dio.hiokdev.restaurant_orders.order_service.api.dto;

import lombok.Builder;
import me.dio.hiokdev.restaurant_orders.order_service.domain.model.Order;

import java.time.OffsetDateTime;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID customerId,
        String description,
        long value,
        OffsetDateTime createdAt
) {

    @Builder
    public OrderResponse {
    }

    public static OrderResponse fromDomain(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .description(order.getDescription())
                .value(order.getValue())
                .createdAt(order.getCreatedAt())
                .build();
    }

}
