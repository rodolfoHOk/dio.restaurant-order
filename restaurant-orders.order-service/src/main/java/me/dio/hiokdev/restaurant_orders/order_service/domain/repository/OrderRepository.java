package me.dio.hiokdev.restaurant_orders.order_service.domain.repository;

import me.dio.hiokdev.restaurant_orders.order_service.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
