package com.example.petstore.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "Owner")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {
    private String firstName;
    private String lastName;
    private String email;

    private Double budget;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "owner")
    @JsonBackReference         // it's a backrefrence
    private List<Pet> pets;    // because pet and owner are bidirectional
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(String firstName, String lastName, String email, Double budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.budget = budget;
        this.pets=new ArrayList<>();
    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }
    public void addPet(Pet pet){
        pets.add(pet);
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Double getBudget() {
        return budget;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Long getId() {
        return id;
    }
}
