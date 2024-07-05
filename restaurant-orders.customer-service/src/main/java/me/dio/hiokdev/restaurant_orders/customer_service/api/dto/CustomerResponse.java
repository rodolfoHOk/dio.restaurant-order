package me.dio.hiokdev.restaurant_orders.customer_service.api.dto;

import lombok.Builder;
import me.dio.hiokdev.restaurant_orders.customer_service.domain.model.Customer;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CustomerResponse(
        UUID id,
        String name,
        String address,
        OffsetDateTime createdAt
) {

    @Builder
    public CustomerResponse {}

    public static CustomerResponse fromDomain(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(customer.getAddress())
                .createdAt(customer.getCreatedAt())
                .build();
    }

}
