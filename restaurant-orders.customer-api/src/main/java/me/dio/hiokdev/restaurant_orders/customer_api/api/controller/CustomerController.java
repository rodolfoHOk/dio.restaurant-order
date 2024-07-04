package me.dio.hiokdev.restaurant_orders.customer_api.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.dio.hiokdev.restaurant_orders.customer_api.api.dto.CustomerRequest;
import me.dio.hiokdev.restaurant_orders.customer_api.api.dto.CustomerResponse;
import me.dio.hiokdev.restaurant_orders.customer_api.domain.service.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        var customers = customerService.getAll();
        return ResponseEntity.ok(customers.stream().map(CustomerResponse::fromDomain).toList());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable(value = "id") UUID customerId) {
        var customer = customerService.getById(customerId);
        return ResponseEntity.ok(CustomerResponse.fromDomain(customer));
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest requestBody) {
        var customer = customerService.create(CustomerRequest.toDomain(requestBody));
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerResponse.fromDomain(customer));
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable(value = "id") UUID customerId,
            @Valid @RequestBody CustomerRequest requestBody
    ) {
        var updateCustomer = customerService.updateById(customerId, CustomerRequest.toDomain(requestBody));
        return ResponseEntity.ok(CustomerResponse.fromDomain(updateCustomer));
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable(value = "id") UUID customerId) {
        var result = customerService.deleteById(customerId);
        return ResponseEntity.ok(result);
    }

}
