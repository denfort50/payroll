package ru.dkalchenko.payroll.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dkalchenko.payroll.model.Order;
import ru.dkalchenko.payroll.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository repository;


    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }
}
