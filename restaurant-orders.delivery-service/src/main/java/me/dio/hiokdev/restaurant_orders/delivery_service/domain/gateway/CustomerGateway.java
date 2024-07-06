package me.dio.hiokdev.restaurant_orders.delivery_service.domain.gateway;

import me.dio.hiokdev.restaurant_orders.delivery_service.domain.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("customer-service")
public interface CustomerGateway {

    @GetMapping(value = "/api/v1/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDTO getById(@PathVariable UUID id);

}
