package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    private PetRepository petRepository;
    private CustomerService customerService;

    public PetService(PetRepository petRepository, CustomerService customerService) {
        this.petRepository = petRepository;
        this.customerService = customerService;
    }

    public Pet savePet(PetDTO petDTO) {
        Pet pet = petRepository.save(convertPetDTOToPet(petDTO));
        customerService.addPetToOwner(pet);
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


}
