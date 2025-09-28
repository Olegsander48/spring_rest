package com.udemy.rest.controller;

import com.udemy.rest.model.Employee;
import com.udemy.rest.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping({"", "/", "/index", "/main"})
    public String getIndex() {
        return "Hello world";
    }

    @GetMapping("/employees")
    public List<Employee> getALl() {
        return employeeService.findAll();
    }
}
