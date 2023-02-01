package com.example.petstore.Service.impl;

import com.example.petstore.Model.Exceptions.PetNotFoundException;
import com.example.petstore.Model.Pet;
import com.example.petstore.Repository.PetJpaRepository;
import com.example.petstore.Service.PetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImp implements PetService {
    private final PetJpaRepository petJpaRepository;

    public PetServiceImp(PetJpaRepository petJpaRepository) {
        this.petJpaRepository = petJpaRepository;
    }

    @Override
    public List<Pet> listAll() {
        return this.petJpaRepository.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return this.petJpaRepository.findById(id)
                .orElseThrow(()->new PetNotFoundException(String.format("Pet with id:%f doesnt exist",id)));
    }

    @Override
    public Pet create(Pet pet) {
        return this.petJpaRepository.save(pet);
    }

    @Override
    public void deleteAllPets() {
        this.petJpaRepository.deleteAll();
    }
}
