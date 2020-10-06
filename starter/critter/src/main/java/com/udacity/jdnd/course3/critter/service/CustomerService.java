package com.udacity.jdnd.course3.critter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
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

    /*
    We received a MANAGED Pet instance
     */
    public Customer addPetToOwner(Pet pet) {
        Customer owner = pet.getOwner();
        if (owner == null) new AppException();
        List<Pet> pets = pet.getOwner().getPets();
        if (pets == null) {
            pets = new ArrayList<>();
            owner.setPets(pets);
        }
        pets.add(pet);
        return repository.save(owner);
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }


}
