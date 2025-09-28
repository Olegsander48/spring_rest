package com.udemy.rest.service;

import com.udemy.rest.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    void saveEmployee(Employee employee);

    Employee findById(int id);

    void remove(int id);
}
