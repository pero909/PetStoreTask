package com.example.petstore.Service;

import com.example.petstore.Model.Pet;
import com.example.petstore.Model.User;

import java.util.List;

public interface UserService {
    Pet buyPet(Pet pet, User user);
    List<User> listAll();
    void save(User user);
    void deleteAllUsers();
}
