package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }
}
