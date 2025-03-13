package com.springframework.boot.demo_project.service;

import com.springframework.boot.demo_project.dto.Customer;

public interface CustomerService {

    Customer createCustomer(Customer customer);
    Customer getCustomerById(Long id);
}
