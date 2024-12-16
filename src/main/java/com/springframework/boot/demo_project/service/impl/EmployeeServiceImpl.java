package com.springframework.boot.demo_project.service.impl;

import com.springframework.boot.demo_project.component.EmployeeTransformer;
import com.springframework.boot.demo_project.dto.Employee;
import com.springframework.boot.demo_project.repository.EmployeeRepository;
import com.springframework.boot.demo_project.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeTransformer employeeTransformer;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeTransformer.employeeEntityToEmployeeDto(employeeRepository.save(employeeTransformer.employeeDtoToEmployeeEntity(employee)));
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeTransformer.employeeEntityToEmployeeDto(employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id)));
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeTransformer.employeeEntityListToEmployeeDtoList(employeeRepository.findAll());
    }

    @Override
    public Employee updateEmployee(Employee updatedEmployee, Long id) {
        return employeeTransformer.employeeEntityToEmployeeDto(
                employeeRepository.findById(id)
                        .map(employee -> {
                            employee.setName(updatedEmployee.getName());
                            employee.setEmpNumber(updatedEmployee.getEmpNumber());
                            return employeeRepository.save(employee);
                        }).orElseGet(() -> employeeRepository.save(employeeTransformer.employeeDtoToEmployeeEntity(updatedEmployee))));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
