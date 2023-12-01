package com.example.test.service;

import com.example.test.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    List<Customer> all();

    Customer getCustomerCustomerById(Long id);

    Customer updateCustomer(Customer customer, Long id);

    void deleteCustomer(Long id);
}
