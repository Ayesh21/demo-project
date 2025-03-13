package com.springframework.boot.demo_project.component;

import com.springframework.boot.demo_project.dto.Customer;
import com.springframework.boot.demo_project.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerTransformer {

    public Customer customerEntityToCustomer(CustomerEntity customerEntity){
        if (customerEntity == null) {
            return null;
        }
        return new Customer(customerEntity.getId(),customerEntity.getName(), customerEntity.getContactNumber());
    }

    public CustomerEntity customerToCustomerEntity(Customer customer){
        if(customer == null){
            return null;
        }
        return CustomerEntity.builder()
                .name(customer.name())
                .contactNumber(customer.contactNumber())
                .build();
    }
}
