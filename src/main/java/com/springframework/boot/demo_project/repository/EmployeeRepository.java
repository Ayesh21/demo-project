package com.springframework.boot.demo_project.repository;

import com.springframework.boot.demo_project.dto.Employee;
import com.springframework.boot.demo_project.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
