package me.dio.hiokdev.restaurant_orders.order_service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import me.dio.hiokdev.restaurant_orders.order_service.domain.model.Order;

import java.util.UUID;

public record OrderRequest(
        @NotBlank
        @Size(min = 36, max = 36)
        String customerId,

        @NotBlank
        @Size(min = 3, max = 255)
        String description,

        @NotNull
        @Positive
        long value
) {

    @Builder
    public OrderRequest {
    }

    public static Order toDomain(OrderRequest orderRequest) {
        return Order.builder()
                .customerId(UUID.fromString(orderRequest.customerId()))
                .description(orderRequest.description())
                .value(orderRequest.value())
                .build();
    }

}
