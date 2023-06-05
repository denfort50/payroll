package ru.dkalchenko.payroll.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dkalchenko.payroll.model.Employee;
import ru.dkalchenko.payroll.model.Order;
import ru.dkalchenko.payroll.model.Status;
import ru.dkalchenko.payroll.service.EmployeeService;
import ru.dkalchenko.payroll.service.OrderService;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initDatabase(EmployeeService employeeService, OrderService orderService) {
        return args -> {
            employeeService.save(new Employee("Bilbo", "Baggins", "burglar"));
            employeeService.save(new Employee("Frodo", "Baggins", "thief"));
            employeeService.findAll().forEach(employee -> log.info("Preloaded " + employee));
            orderService.save(new Order("MacBook Pro", Status.COMPLETED));
            orderService.save(new Order("iPhone", Status.IN_PROGRESS));
            orderService.findAll().forEach(order -> log.info("Preloaded " + order));
        };
    }
}
