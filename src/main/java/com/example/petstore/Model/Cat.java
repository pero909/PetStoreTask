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
public class Cat extends Pet{


    public Cat(User owner, String name, Type type, String description, LocalDate birthDate) {
        super(owner, name, type, description, birthDate);
        Period period = Period.between(birthDate,LocalDate.now());
        this.setPrice(period.getYears());


    }

    public Cat() {

    }
}
