package com.example.test.repository;

import com.example.test.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {



//    Optional<Customer> findByFirstNameAndLastName(Customer customer);

    Optional<Customer> findByPhoneNumber(String phoneNumber);
}
