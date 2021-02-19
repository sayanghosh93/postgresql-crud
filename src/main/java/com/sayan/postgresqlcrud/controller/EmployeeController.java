package com.sayan.postgresqlcrud.controller;

import com.sayan.postgresqlcrud.exception.ErrorDetails;
import com.sayan.postgresqlcrud.exception.GlobalExceptionHandler;
import com.sayan.postgresqlcrud.exception.ResourceNotFoundException;
import com.sayan.postgresqlcrud.model.Employee;
import com.sayan.postgresqlcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        try {
            return employeeService.getEmployeeById(id);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        ResponseEntity<Employee> employeeResponseEntity;
        try {
            employeeResponseEntity = employeeService.updateEmployee(id, employee);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            employeeResponseEntity = ResponseEntity.notFound().build();
        }
        return employeeResponseEntity;
    }

    @DeleteMapping("employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable("id") long id) {
        Map<String, Boolean> map = new HashMap<>();
        try {
            employeeService.deleteEmployee(id);
            map.put("deleted", Boolean.TRUE);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            map.put("deleted", Boolean.FALSE);
        }
        return map;
    }

}
