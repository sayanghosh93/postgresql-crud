package com.sayan.postgresqlcrud.service;

import com.sayan.postgresqlcrud.exception.ResourceNotFoundException;
import com.sayan.postgresqlcrud.model.Employee;
import com.sayan.postgresqlcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<Employee> getEmployeeById(long id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + id));
        return ResponseEntity.ok().body(employee);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public ResponseEntity<Employee> updateEmployee(long id, Employee entity) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + id));
        employee.setEmail(entity.getEmail());
        employee.setLastName(entity.getFirstName());
        employee.setLastName(entity.getLastName());

        return ResponseEntity.ok(employeeRepository.save(employee));
    }

    public ResponseEntity<Employee> deleteEmployee(long id) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id: " + id));

        employeeRepository.delete(employee);

        return ResponseEntity.ok(employeeRepository.save(employee));
    }

}
