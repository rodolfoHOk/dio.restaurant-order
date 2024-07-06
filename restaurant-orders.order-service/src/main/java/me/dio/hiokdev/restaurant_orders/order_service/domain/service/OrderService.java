package me.dio.hiokdev.restaurant_orders.order_service.domain.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dio.hiokdev.restaurant_orders.order_service.domain.exception.BadRequestException;
import me.dio.hiokdev.restaurant_orders.order_service.domain.exception.GatewayException;
import me.dio.hiokdev.restaurant_orders.order_service.domain.exception.OrderException;
import me.dio.hiokdev.restaurant_orders.order_service.domain.exception.ResourceNotFoundException;
import me.dio.hiokdev.restaurant_orders.order_service.domain.gateway.CustomerGateway;
import me.dio.hiokdev.restaurant_orders.order_service.domain.model.Order;
import me.dio.hiokdev.restaurant_orders.order_service.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerGateway customerGateway;

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found :: " + id));
    }

    public Order create(Order order) {
        validateCustomer(order);
        try {
            return orderRepository.save(order);
        } catch (Exception exception) {
            throw new OrderException(exception.getMessage());
        }
    }

    public Order updateById(UUID id, Order order) {
        var existOrder = this.getById(id);
        validateCustomer(order);
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

    private void validateCustomer(Order order) {
        try {
            var customer = customerGateway.getById(order.getCustomerId());
            order.setCustomerId(UUID.fromString(customer.id()));
        } catch (FeignException.NotFound exception) {
            throw new BadRequestException("Bad Request - Customer not exists :: " + order.getCustomerId());
        } catch (FeignException exception) {
            log.error("Bad Gateway :: {} :: {} ", exception.getClass().getCanonicalName(), exception.getMessage());
            throw new GatewayException("Bad Gateway :: Try again later");
        }
    }

}
