package me.dio.hiokdev.restaurant_orders.order_service.domain.service;

import lombok.RequiredArgsConstructor;
import me.dio.hiokdev.restaurant_orders.order_service.domain.exception.OrderException;
import me.dio.hiokdev.restaurant_orders.order_service.domain.exception.ResourceNotFoundException;
import me.dio.hiokdev.restaurant_orders.order_service.domain.model.Order;
import me.dio.hiokdev.restaurant_orders.order_service.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found :: " + id));
    }

    public Order create(Order order) {
        try {
            return orderRepository.save(order);
        } catch (Exception exception) {
            throw new OrderException(exception.getMessage());
        }
    }

    public Order updateById(UUID id, Order order) {
        var existOrder = this.getById(id);
        existOrder.setCustomerId(order.getCustomerId());
        existOrder.setDescription(order.getDescription());
        existOrder.setValue(order.getValue());
        try {
            return orderRepository.save(existOrder);
        } catch (Exception exception) {
            throw new OrderException(exception.getMessage());
        }
    }

    public Map<String, Boolean> deleteById(UUID id) {
        var order = this.getById(id);
        try {
            orderRepository.delete(order);
        } catch (Exception exception) {
            throw new OrderException(exception.getMessage());
        }
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", Boolean.TRUE);
        return result;
    }

}
