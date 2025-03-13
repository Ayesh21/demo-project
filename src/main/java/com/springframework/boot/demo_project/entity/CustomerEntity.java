package com.springframework.boot.demo_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {
        @Id
        @GeneratedValue(strategy =GenerationType.IDENTITY)
        @Column(unique=true, nullable=false, precision=10)
        private Long id;
        private String name;
        @Column(name="contact_number", length = 10)
        private String contactNumber;
}


