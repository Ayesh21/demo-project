package com.springframework.boot.demo_project.controller;

import com.springframework.boot.demo_project.dto.Customer;
import com.springframework.boot.demo_project.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    /**
     * Create Customers
     * @param customer object
     * @return a saved customer
     */
    @PostMapping
    public Customer createCustomer(@Validated @RequestBody Customer customer){
        logger.info("Creating customer: {}", customer);
        return customerService.createCustomer(customer);
    }

    /**
     * Get all the created Customers
     * @return a new customer
     */
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable(value = "id") Long id){
        logger.info("Fetching customer by ID: {}", id);
        return customerService.getCustomerById(id);
    }
}
