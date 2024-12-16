package com.springframework.boot.demo_project.component;

import com.springframework.boot.demo_project.dto.Employee;
import com.springframework.boot.demo_project.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeTransformer {

    public Employee employeeEntityToEmployeeDto(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }
        return Employee.builder()
                .id(entity.getId())
                .name(entity.getName())
                .empNumber(entity.getEmpNumber())
                .build();
    }

    public EmployeeEntity employeeDtoToEmployeeEntity(Employee dto) {
        if (dto == null) {
            return null;
        }
        EmployeeEntity entity = new EmployeeEntity();
        entity.setName(dto.getName());
        entity.setEmpNumber(dto.getEmpNumber());
        return entity;
    }

    public List<Employee> employeeEntityListToEmployeeDtoList(List<EmployeeEntity> entities) {
        if(entities == null || entities.isEmpty()){
            return List.of();
        }
        return entities.stream().map(this :: employeeEntityToEmployeeDto).toList();
    }
}

