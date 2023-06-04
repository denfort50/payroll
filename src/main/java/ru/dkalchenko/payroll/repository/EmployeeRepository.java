package ru.dkalchenko.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dkalchenko.payroll.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
