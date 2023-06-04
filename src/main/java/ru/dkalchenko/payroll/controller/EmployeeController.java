package ru.dkalchenko.payroll.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dkalchenko.payroll.exception.EmployeeNotFoundException;
import ru.dkalchenko.payroll.model.Employee;
import ru.dkalchenko.payroll.service.EmployeeService;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public List<Employee> all() {
        return service.findAll();
    }

    @PostMapping
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return service.save(newEmployee);
    }

    @GetMapping("/{id}")
    public Employee one(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return service.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return service.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return service.save(newEmployee);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteById(id);
    }
}
