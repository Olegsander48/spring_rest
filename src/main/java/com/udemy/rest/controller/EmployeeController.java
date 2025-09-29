package com.udemy.rest.controller;

import com.udemy.rest.exceptionhandling.EmployeeIncorrectData;
import com.udemy.rest.exceptionhandling.NoSuchEmployeeException;
import com.udemy.rest.model.Employee;
import com.udemy.rest.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * Handler for {@link NoSuchEmployeeException}
     * @param exception handle exception
     * @return response with status 404 and message from exception
     */
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    /**
     * Handler for all other exceptions except {@link NoSuchEmployeeException}
     * @param exception handle exception
     * @return response with status 400 and message from exception
     */
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
