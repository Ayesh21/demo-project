package com.springframework.boot.demo_project.service;

import com.springframework.boot.demo_project.component.EmployeeTransformer;
import com.springframework.boot.demo_project.dto.Employee;
import com.springframework.boot.demo_project.entity.EmployeeEntity;
import com.springframework.boot.demo_project.repository.EmployeeRepository;
import com.springframework.boot.demo_project.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeTransformer employeeTransformer;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;
    private EmployeeEntity employeeEntity;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setEmpNumber("E00100");
        employee.setEmpType("Part Time");

        employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        employeeEntity.setName("John Doe");
        employeeEntity.setEmpNumber("E00100");
        employeeEntity.setEmpType("Part Time");
    }

    @Test
    void createEmployeeTest() {
        when(employeeTransformer.employeeDtoToEmployeeEntity(any(Employee.class))).thenReturn(employeeEntity);
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employeeEntity);
        when(employeeTransformer.employeeEntityToEmployeeDto(any(EmployeeEntity.class))).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
    }

    @Test
    void getEmployeeByIdTest() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity));
        when(employeeTransformer.employeeEntityToEmployeeDto(employeeEntity)).thenReturn(employee);

        Employee result = employeeService.getEmployeeById(1L);

        assertNotNull(result);
        assertEquals(employee.getId(), result.getId());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeById_NotFoundTest() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(1L));

        assertEquals("Employee not found with ID: 1", exception.getMessage());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void getEmployeeTest() {
        List<EmployeeEntity> employeeEntities = Arrays.asList(employeeEntity);
        List<Employee> employees = Arrays.asList(employee);

        when(employeeRepository.findAll()).thenReturn(employeeEntities);
        when(employeeTransformer.employeeEntityListToEmployeeDtoList(employeeEntities)).thenReturn(employees);

        List<Employee> result = employeeService.getEmployee();

        assertEquals(1, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void updateEmployee_ExistingEmployeeTest() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity));
        when(employeeRepository.save(any(EmployeeEntity.class))).thenAnswer(invocation -> {
            EmployeeEntity savedEntity = invocation.getArgument(0);
            employeeEntity.setName(savedEntity.getName());
            employeeEntity.setEmpNumber(savedEntity.getEmpNumber());
            return employeeEntity;
        });
        when(employeeTransformer.employeeEntityToEmployeeDto(any(EmployeeEntity.class))).thenAnswer(invocation -> {
            EmployeeEntity entity = invocation.getArgument(0);
            return new Employee(entity.getId(), entity.getName(), entity.getEmpNumber(), entity.getEmpType());
        });

        Employee updatedEmployee = new Employee();

        updatedEmployee.setName("John Zena");
        updatedEmployee.setEmpNumber("E00100");

        Employee result = employeeService.updateEmployee(updatedEmployee, 1L);

        assertNotNull(result);
        assertEquals("John Zena", result.getName());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
    }

    @Test
    void updateEmployee_NewEmployeeTest() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        EmployeeEntity newEmployeeEntity = new EmployeeEntity();
        newEmployeeEntity.setName("New Employee");
        newEmployeeEntity.setEmpNumber("EMP999");

        when(employeeTransformer.employeeDtoToEmployeeEntity(any(Employee.class))).thenReturn(newEmployeeEntity);
        when(employeeRepository.save(any(EmployeeEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(employeeTransformer.employeeEntityToEmployeeDto(any(EmployeeEntity.class))).thenAnswer(invocation -> {
            EmployeeEntity entity = invocation.getArgument(0);
            return new Employee(entity.getId(), entity.getName(), entity.getEmpNumber(), entity.getEmpType());
        });

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("New Employee");
        updatedEmployee.setEmpNumber("EMP999");

        Employee result = employeeService.updateEmployee(updatedEmployee, 1L);

        assertNotNull(result);
        assertEquals("New Employee", result.getName());
        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
    }


    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById(1L);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(1L);
    }
}

