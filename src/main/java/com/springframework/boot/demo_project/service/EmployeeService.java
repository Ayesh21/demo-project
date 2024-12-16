package com.springframework.boot.demo_project.service;

import com.springframework.boot.demo_project.dto.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee createEmployee(Employee employee);
    public Employee getEmployee(Long id);
    public List<Employee> getEmployees();
    public Employee updateEmployee(Employee employee,Long id);
    public void deleteEmployee(Long id);
}
