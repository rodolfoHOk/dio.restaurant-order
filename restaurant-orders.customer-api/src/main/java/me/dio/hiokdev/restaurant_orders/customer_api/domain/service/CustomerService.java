package me.dio.hiokdev.restaurant_orders.customer_api.domain.service;

import lombok.RequiredArgsConstructor;
import me.dio.hiokdev.restaurant_orders.customer_api.domain.exception.CustomerException;
import me.dio.hiokdev.restaurant_orders.customer_api.domain.exception.ResourceNotFoundException;
import me.dio.hiokdev.restaurant_orders.customer_api.domain.model.Customer;
import me.dio.hiokdev.restaurant_orders.customer_api.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(UUID id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found :: " + id));
    }

    public Customer create(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (Exception exception) {
            throw new CustomerException(exception.getMessage());
        }
    }

    public Customer updateById(UUID id, Customer customer) {
        var existCustomer = this.getById(id);
        existCustomer.setName(customer.getName());
        existCustomer.setAddress(customer.getAddress());
        try {
            return customerRepository.save(existCustomer);
        } catch (Exception exception) {
            throw new CustomerException(exception.getMessage());
        }
    }

    public Map<String, Boolean> deleteById(UUID id) {
        var customer = this.getById(id);
        try {
            customerRepository.delete(customer);
        } catch (Exception exception) {
            throw new CustomerException(exception.getMessage());
        }
        Map<String, Boolean> result = new HashMap<>();
        result.put("deleted", Boolean.TRUE);
        return result;
    }

}
