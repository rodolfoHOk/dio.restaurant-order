package me.dio.hiokdev.restaurant_orders.delivery_service.domain.service;

import lombok.RequiredArgsConstructor;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.exception.DeliveryException;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.exception.ResourceNotFoundException;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.model.Delivery;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    public Delivery getById(UUID id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found :: " + id));
    }

    public Delivery create(Delivery delivery) {
        try {
            return deliveryRepository.save(delivery);
        } catch (Exception exception) {
            throw new DeliveryException(exception.getMessage());
        }
    }

    public Delivery updateById(UUID id, Delivery delivery) {
        var existEntity = this.getById(id);
        existEntity.setOrderId(delivery.getOrderId());
        existEntity.setCustomerId(delivery.getCustomerId());
        existEntity.setAddress(delivery.getAddress());
        existEntity.setShippingValue(delivery.getShippingValue());
        try {
            return deliveryRepository.save(existEntity);
        } catch (Exception exception) {
            throw new DeliveryException(exception.getMessage());
        }
    }

    public Map<String, Boolean> deleteById(UUID id) {
        var existEntity = this.getById(id);
        try {
            deliveryRepository.delete(existEntity);
        } catch (Exception exception) {
            throw new DeliveryException(exception.getMessage());
        }
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", true);
        return result;
    }

}
