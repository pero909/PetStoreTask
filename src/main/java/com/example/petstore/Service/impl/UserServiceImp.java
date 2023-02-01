package com.example.petstore.Service.impl;

import com.example.petstore.Model.Enum.Type;
import com.example.petstore.Model.Exceptions.InsufficientFundsException;
import com.example.petstore.Model.Exceptions.petHasOwnerException;
import com.example.petstore.Model.Pet;
import com.example.petstore.Model.User;
import com.example.petstore.Repository.PetJpaRepository;
import com.example.petstore.Repository.UserJpaRepository;
import com.example.petstore.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final PetJpaRepository petJpaRepository;

    public UserServiceImp(UserJpaRepository userJpaRepository, PetJpaRepository petJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.petJpaRepository = petJpaRepository;
    }

    public User create(String firstName, String lastName, String email, Double  budget){
        User user = new User(firstName,lastName,email,budget);
        return this.userJpaRepository.save(user);
    }


    @Override
    public Pet buyPet(Pet pet, User user) {
        if(user.getBudget()<pet.getPrice()){

            throw new InsufficientFundsException("Not enough funds");

        }else if(pet.hasOwner()){
            throw new petHasOwnerException(String.format("%s's owner is %s ",
                    pet.getName(),pet.getOwner().getFirstName()));
        }else{
            pet.setOwner(user);
            this.petJpaRepository.save(pet);

            user.addPet(pet);
            user.setBudget(user.getBudget()-pet.getPrice());
            this.userJpaRepository.save(user);


           if(pet.getType().equals(Type.Cat)){
               System.out.printf("Meow,Cat %s has owner %s%n",
                       pet.getName(),user.getFirstName());
           }else {
               System.out.printf("Woof,Dog %s has owner %s%n",
                       pet.getName(), user.getFirstName());
           }

            return pet;
        }
    }

    @Override
    public List<User> listAll() {
        return this.userJpaRepository.findAll();
    }

    @Override
    public void save(User user) {
        this.userJpaRepository.save(user);
    }

    @Override
    public void deleteAllUsers() {
        this.userJpaRepository.deleteAll();
    }
}
