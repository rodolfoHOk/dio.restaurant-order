package me.dio.hiokdev.restaurant_orders.delivery_service.domain.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.exception.BadRequestException;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.exception.DeliveryException;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.exception.GatewayException;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.exception.ResourceNotFoundException;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.gateway.CustomerGateway;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.gateway.OrderGateway;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.model.Delivery;
import me.dio.hiokdev.restaurant_orders.delivery_service.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CustomerGateway customerGateway;
    private final OrderGateway orderGateway;

    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    public Delivery getById(UUID id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery not found :: " + id));
    }

    public Delivery create(Delivery delivery) {
        validateOrder(delivery);
        validateCustomer(delivery);
        try {
            return deliveryRepository.save(delivery);
        } catch (Exception exception) {
            throw new DeliveryException(exception.getMessage());
        }
    }

    public Delivery updateById(UUID id, Delivery delivery) {
        var existEntity = this.getById(id);
        validateOrder(delivery);
        validateCustomer(delivery);
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

    private void validateCustomer(Delivery delivery) {
        try {
            var customer = customerGateway.getById(delivery.getCustomerId());
            delivery.setCustomerId(UUID.fromString(customer.id()));
        } catch (FeignException.NotFound exception) {
            throw new BadRequestException("Bad Request - Customer not exists :: " + delivery.getCustomerId());
        } catch (FeignException exception) {
            log.error("Customer Bad Gateway :: {} :: {} ", exception.getClass().getCanonicalName(), exception.getMessage());
            throw new GatewayException("Bad Gateway :: Try again later");
        }
    }

    private void validateOrder(Delivery delivery) {
        try {
            var order = orderGateway.getById(delivery.getOrderId());
            delivery.setOrderId(UUID.fromString(order.id()));
        } catch (FeignException.NotFound exception) {
            throw new BadRequestException("Bad Request - Order not exists :: " + delivery.getCustomerId());
        } catch (FeignException exception) {
            log.error("Order Bad Gateway :: {} :: {} ", exception.getClass().getCanonicalName(), exception.getMessage());
            throw new GatewayException("Bad Gateway :: Try again later");
        }
    }

}
