package ru.dkalchenko.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dkalchenko.payroll.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
