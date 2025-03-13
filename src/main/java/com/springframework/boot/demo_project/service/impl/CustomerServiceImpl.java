package com.springframework.boot.demo_project.service.impl;

import com.springframework.boot.demo_project.component.CustomerTransformer;
import com.springframework.boot.demo_project.dto.Customer;
import com.springframework.boot.demo_project.repository.CustomerRepository;
import com.springframework.boot.demo_project.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerTransformer customerTransformer;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerTransformer.customerEntityToCustomer(customerRepository.save(customerTransformer.customerToCustomerEntity(customer)));
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerTransformer.customerEntityToCustomer(customerRepository.findById(id).orElseThrow(()-> new RuntimeException("Customer no found "+id)));
    }
}
