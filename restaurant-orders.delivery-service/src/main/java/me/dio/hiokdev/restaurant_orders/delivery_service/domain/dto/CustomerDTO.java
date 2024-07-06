package me.dio.hiokdev.restaurant_orders.delivery_service.domain.dto;

public record CustomerDTO(
        String id,
        String name,
        String address,
        String createdAt
) {
}
