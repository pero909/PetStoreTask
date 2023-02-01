package com.example.petstore.Service;

import com.example.petstore.Model.Enum.Type;
import com.example.petstore.Model.Pet;
import com.example.petstore.Model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PetService {
    List<Pet> listAll();
    Pet findById(Long id);
    Pet create(Pet pet);
    void deleteAllPets();
}
