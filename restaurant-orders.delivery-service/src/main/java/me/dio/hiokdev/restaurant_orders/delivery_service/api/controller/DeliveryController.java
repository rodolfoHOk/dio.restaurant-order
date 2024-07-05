package me.dio.hiokdev.restaurant_orders.delivery_service.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.dio.hiokdev.restaurant_orders.delivery_service.api.dto.DeliveryRequest;
import me.dio.hiokdev.restaurant_orders.delivery_service.api.dto.DeliveryResponse;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.service.DeliveryService;
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
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/deliveries")
    public ResponseEntity<List<DeliveryResponse>> getAll() {
        var entities = deliveryService.getAll();
        return ResponseEntity.ok(entities.stream().map(DeliveryResponse::fromDomain).toList());
    }

    @GetMapping("/delivery/{id}")
    public ResponseEntity<DeliveryResponse> getById(@PathVariable(value = "id") UUID id) {
        var entity = deliveryService.getById(id);
        return ResponseEntity.ok(DeliveryResponse.fromDomain(entity));
    }

    @PostMapping("/delivery")
    public ResponseEntity<DeliveryResponse> create(@Valid @RequestBody DeliveryRequest requestBody) {
        var entity = deliveryService.create(DeliveryRequest.toDomain(requestBody));
        return ResponseEntity.status(HttpStatus.CREATED).body(DeliveryResponse.fromDomain(entity));
    }

    @PutMapping("/delivery/{id}")
    public ResponseEntity<DeliveryResponse> updateById(
            @PathVariable(value = "id") UUID id,
            @Valid @RequestBody DeliveryRequest requestBody
    ) {
        var entity = deliveryService.updateById(id, DeliveryRequest.toDomain(requestBody));
        return ResponseEntity.ok(DeliveryResponse.fromDomain(entity));
    }

    @DeleteMapping("/delivery/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteById(@PathVariable(value = "id") UUID id) {
        var result = deliveryService.deleteById(id);
        return ResponseEntity.ok(result);
    }

}
