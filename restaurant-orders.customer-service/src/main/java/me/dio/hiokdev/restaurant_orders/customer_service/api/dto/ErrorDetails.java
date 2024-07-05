package me.dio.hiokdev.restaurant_orders.customer_service.api.dto;

import lombok.Builder;

import java.time.OffsetDateTime;

public record ErrorDetails(
        Integer status,
        String message,
        String details,
        OffsetDateTime timestamp
) {

    @Builder
    public ErrorDetails {}

}
