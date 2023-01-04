package com.example.Stream_API.Service;

import com.example.Stream_API.Controller.CustomerController;
import com.example.Stream_API.Repo.CustomerRepo;
import com.example.Stream_API.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    public Customer getCustomerById(Long id){
        return customerRepo.findById(id).orElse(null);
    }
    public void setCustomers(Customer customer){
        customerRepo.save(customer);
    }
}
