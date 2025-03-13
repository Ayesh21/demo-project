package com.springframework.boot.demo_project.service;

import com.springframework.boot.demo_project.dto.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getEmployee();
    Employee updateEmployee(Employee employee,Long id);
    void deleteEmployee(Long id);
}
