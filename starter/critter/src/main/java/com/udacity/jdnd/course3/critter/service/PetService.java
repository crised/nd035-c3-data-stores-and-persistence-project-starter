package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
public class PetService {

    private PetRepository petRepository;
    private CustomerRepository customerRepository;
    private CustomerService customerService; // TODO: Fix this

    public PetService(PetRepository petRepository, CustomerRepository customerRepository, CustomerService customerService) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    public Pet savePet(PetDTO petDTO) {
        Pet pet = petRepository.save(convertPetDTOToPet(petDTO));
        // Should be in Owner Service logic
        Customer owner = pet.getOwner();
        List<Pet> pets = pet.getOwner().getPets();
        if (pets == null) {
            pets = new ArrayList<>();
            pets.add(pet);
            owner.setPets(pets);
        } else pets.add(pet);
        customerRepository.save(owner);
        //
        return pet;
    }

    public Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setOwner(customerService.findCustomerById(petDTO.getOwnerId()));
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    public Pet findPetById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) return optionalPet.get();
        throw new AppException();
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

}
