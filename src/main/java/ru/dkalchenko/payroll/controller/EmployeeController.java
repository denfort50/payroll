package ru.dkalchenko.payroll.controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import ru.dkalchenko.payroll.exception.EmployeeNotFoundException;
import ru.dkalchenko.payroll.model.Employee;
import ru.dkalchenko.payroll.service.EmployeeService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    private final EmployeeModelAssembler assembler;

    @GetMapping()
    public CollectionModel<EntityModel<Employee>> all() {
        List<EntityModel<Employee>> employees = service.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return service.save(newEmployee);
    }

    @GetMapping("/{id}")
    public EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee = service.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return assembler.toModel(employee);
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
