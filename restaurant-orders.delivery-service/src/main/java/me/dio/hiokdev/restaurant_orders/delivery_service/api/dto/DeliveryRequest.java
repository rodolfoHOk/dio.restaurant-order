package me.dio.hiokdev.restaurant_orders.delivery_service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.model.Delivery;

import java.util.UUID;

public record DeliveryRequest(
        @NotBlank
        @Size(min = 36, max = 36)
        String orderId,

        @NotBlank
        @Size(min = 36, max = 36)
        String customerId,

        @NotBlank
        @Size(min = 3, max = 255)
        String address,

        @NotNull
        @PositiveOrZero
        long shippingValue
) {

    @Builder
    public DeliveryRequest {
    }

    public static Delivery toDomain(DeliveryRequest request) {
        return Delivery.builder()
                .orderId(UUID.fromString(request.orderId()))
                .customerId(UUID.fromString(request.customerId()))
                .address(request.address())
                .shippingValue(request.shippingValue())
                .build();
    }

}
