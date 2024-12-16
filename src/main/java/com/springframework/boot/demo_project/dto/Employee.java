package com.springframework.boot.demo_project.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private Long empNumber;


}
