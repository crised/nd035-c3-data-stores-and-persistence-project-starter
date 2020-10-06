package com.udacity.jdnd.course3.critter.service;

import java.util.List;
import java.util.Optional;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer saveCustomer(CustomerDTO customerDTO) {
        Customer persistedCustomer = repository.save(convertCustomerDTOToCustomer(customerDTO));
        return persistedCustomer;
    }

    public Customer findCustomerById(Long id) {
        Optional<Customer> optionalCustomer = repository.findById(id);
        if (optionalCustomer.isPresent()) return optionalCustomer.get();
        throw new AppException();
    }

    public List<Customer> getAllCustomers() {
        return (List<Customer>) repository.findAll();
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }


}
