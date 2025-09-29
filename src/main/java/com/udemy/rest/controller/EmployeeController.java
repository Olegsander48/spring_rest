package com.udemy.rest.controller;

import com.udemy.rest.exceptionhandling.NoSuchEmployeeException;
import com.udemy.rest.model.Employee;
import com.udemy.rest.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id: " + id);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with id: " + id);
        }
        employeeService.remove(id);
        return "Employee with id: " + id + " deleted";
    }
}
