package ru.dkalchenko.payroll.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dkalchenko.payroll.model.Employee;
import ru.dkalchenko.payroll.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee save(Employee newEmployee) {
        return repository.save(newEmployee);
    }

    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
