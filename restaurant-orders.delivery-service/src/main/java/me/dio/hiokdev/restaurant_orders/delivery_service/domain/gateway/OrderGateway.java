package me.dio.hiokdev.restaurant_orders.delivery_service.domain.gateway;

import me.dio.hiokdev.restaurant_orders.delivery_service.domain.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("order-service")
public interface OrderGateway {

    @GetMapping(value = "/api/v1/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    OrderDTO getById(@PathVariable UUID id);

}
