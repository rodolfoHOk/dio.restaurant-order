package me.dio.hiokdev.restaurant_orders.customer_service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import me.dio.hiokdev.restaurant_orders.customer_service.domain.model.Customer;

public record CustomerRequest(
        @NotBlank
        @Size(min = 3, max = 255)
        String name,

        @NotBlank
        @Size(min = 3, max = 255)
        String address
) {

        public static Customer toDomain(CustomerRequest requestBody) {
                return Customer.builder()
                        .name(requestBody.name())
                        .address(requestBody.address())
                        .build();
        }

}
