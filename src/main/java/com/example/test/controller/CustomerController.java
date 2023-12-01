package com.example.test.controller;

import com.example.test.entity.Customer;
import com.example.test.exeption.AllReadyExist;
import com.example.test.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> add(@RequestBody Customer customer) throws AllReadyExist {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(customerService.all());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Customer> getProductById(@PathVariable Long id){
        Customer customer =  customerService.getCustomerCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        Customer updateCustomer = customerService.updateCustomer(customer, id);
        return new ResponseEntity<>("customer is updated successfully", HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }
}
