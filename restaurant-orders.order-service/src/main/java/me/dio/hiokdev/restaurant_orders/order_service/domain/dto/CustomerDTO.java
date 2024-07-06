package me.dio.hiokdev.restaurant_orders.order_service.domain.dto;

public record CustomerDTO(
        String id,
        String name,
        String address,
        String createdAt
) {
}
