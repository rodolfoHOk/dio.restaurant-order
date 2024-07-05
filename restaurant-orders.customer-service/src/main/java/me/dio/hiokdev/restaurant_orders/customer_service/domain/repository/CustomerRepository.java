package me.dio.hiokdev.restaurant_orders.customer_service.domain.repository;

import me.dio.hiokdev.restaurant_orders.customer_service.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
