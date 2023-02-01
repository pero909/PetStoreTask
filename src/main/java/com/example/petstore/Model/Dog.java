package com.example.petstore.Model;

import com.example.petstore.Model.Enum.Type;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.Period;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "owner")
public class Dog extends Pet {
    private Integer  rating;


    public Dog(User owner, String name, Type type, String description, LocalDate birthDate, Integer  rating) {
        super(owner, name, type, description, birthDate);
        this.rating = rating;
        Period period = Period.between(birthDate,LocalDate.now());
        Integer  price= period.getYears() + rating ;
        this.setPrice(price);



    }

    public Integer  getRating() {
        return rating;
    }


    public Dog() {

    }
}
