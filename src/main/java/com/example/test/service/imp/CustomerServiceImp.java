package com.example.test.service.imp;

import com.example.test.entity.Customer;
import com.example.test.exeption.AllReadyExist;
import com.example.test.exeption.CustomerNotFound;
import com.example.test.exeption.ProductNotFound;
import com.example.test.repository.CustomerRepository;
import com.example.test.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        Optional<Customer> customer1 = customerRepository
                .findByPhoneNumber(customer.getPhoneNumber());
        if (customer1.isPresent()) {
            throw new AllReadyExist("this customer is present");
        } else {
            Customer savedCustomer = new Customer();
            savedCustomer.setPhoneNumber(customer.getPhoneNumber());
            savedCustomer.setFirstName(customer.getFirstName());
            savedCustomer.setLastName(customer.getLastName());

            return customerRepository.save(savedCustomer);
        }
    }

    @Override
    public List<Customer> all() {
        ArrayList<Customer> customers = new ArrayList<>();
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerCustomerById(Long id) {
        Optional<Customer> byId = customerRepository.findById(id) ;
        if (byId.isPresent()){
            return byId.get();
        }else{
            throw new ProductNotFound("productId not found");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        Customer customer1 = customerRepository.findById(id)
                .orElseThrow(() -> new AllReadyExist("Customer not found"));
        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setAddresses(customer.getAddresses());
        return customerRepository.save(customer1);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFound("customer not found"));
        customerRepository.delete(customer);

    }
}
