package me.dio.hiokdev.restaurant_orders.delivery_service.domain.repository;

import me.dio.hiokdev.restaurant_orders.delivery_service.domain.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
}
