package com.wipro.springboot.usecase1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // HashMap for in-memory caching
    private final Map<Long, Employee> employeeCache = new ConcurrentHashMap<>();

    // Add Employee (New Joinee Handling)
    public Employee addEmployee(Employee employee) {
        if (employee.getRole() == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }

        Employee savedEmployee = employeeRepository.save(employee);
        employeeCache.put(savedEmployee.getId(), savedEmployee);  // Cache it
        return savedEmployee;
    }

    // Update Employee Data
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setRole(updatedEmployee.getRole());

            Employee savedEmployee = employeeRepository.save(employee);
            employeeCache.put(id, savedEmployee); // Update HashMap
            return savedEmployee;
        }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    // Fetch Single Employee
    public Employee getEmployee(Long id) {
        return employeeCache.getOrDefault(id, employeeRepository.findById(id).orElse(null));
    }

    // Fetch All Employees
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeCache.values());
    }

    // Delete Employee
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        employeeCache.remove(id);
    }
}