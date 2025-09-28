package com.udemy.rest.repository;

import com.udemy.rest.model.Employee;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();

    void saveEmployee(Employee employee);

    Employee findById(int id);

    void remove(int id);
}
