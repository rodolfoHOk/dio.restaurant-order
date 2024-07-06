package me.dio.hiokdev.restaurant_orders.delivery_service.domain.dto;

public record OrderDTO(
        String id,
        String customerId,
        String description,
        Long value,
        String createdAt
) {
}
