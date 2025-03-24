package com.springframework.boot.demo_project.controller;

import com.springframework.boot.demo_project.dto.Employee;
import com.springframework.boot.demo_project.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmpNumber("E00100");
        employee.setEmpType("Part Time");
    }

    @Test
    void createEmployeeTest() {
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

        Employee result = employeeController.createEmployee(employee);

        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
        verify(employeeService, times(1)).createEmployee(any(Employee.class));
    }

    @Test
    void getEmployeeTest() {
        List<Employee> employees = Arrays.asList(employee);
        when(employeeService.getEmployee()).thenReturn(employees);

        List<Employee> result = employeeController.getEmployee();

        assertEquals(1, result.size());
        verify(employeeService, times(1)).getEmployee();
    }

    @Test
    void getEmployeeByIdTest() {
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        ResponseEntity<Employee> response = employeeController.getEmployeeById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(employee, response.getBody());
        verify(employeeService, times(1)).getEmployeeById(1L);
    }


    @Test
    void updateEmployeeTest() {
        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1L);
        employee.setName("John Doe");
        employee.setEmpNumber("E00100");
        employee.setEmpType("Full Time");// change Part Time to Full Time

        when(employeeService.updateEmployee(any(Employee.class), eq(1L))).thenReturn(updatedEmployee);

        ResponseEntity<Employee> response = employeeController.updateEmployee(updatedEmployee, 1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedEmployee.getEmpType(), response.getBody().getEmpType());
        verify(employeeService, times(1)).updateEmployee(any(Employee.class), eq(1L));
    }

    @Test
    void deleteEmployeeTest() {
        doNothing().when(employeeService).deleteEmployee(1L);

        ResponseEntity<Void> response = employeeController.deleteEmployee(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(employeeService, times(1)).deleteEmployee(1L);
    }

}
