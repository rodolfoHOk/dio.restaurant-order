package me.dio.hiokdev.restaurant_orders.order_service.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.dio.hiokdev.restaurant_orders.order_service.api.dto.OrderRequest;
import me.dio.hiokdev.restaurant_orders.order_service.api.dto.OrderResponse;
import me.dio.hiokdev.restaurant_orders.order_service.domain.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getAll() {
        var entities = orderService.getAll();
        return ResponseEntity.ok(entities.stream().map(OrderResponse::fromDomain).toList());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable(value = "id") UUID id) {
        var entity = orderService.getById(id);
        return ResponseEntity.ok(OrderResponse.fromDomain(entity));
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest requestBody) {
        var entity = orderService.create(OrderRequest.toDomain(requestBody));
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse.fromDomain(entity));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<OrderResponse> updateById(
            @PathVariable(value = "id") UUID id,
            @Valid @RequestBody OrderRequest requestBody
    ) {
        var entity = orderService.updateById(id, OrderRequest.toDomain(requestBody));
        return ResponseEntity.ok(OrderResponse.fromDomain(entity));
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteById(@PathVariable(value = "id") UUID id) {
        var result = orderService.deleteById(id);
        return ResponseEntity.ok(result);
    }

}
