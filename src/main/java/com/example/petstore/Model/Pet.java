package com.example.petstore.Model;

import com.example.petstore.Model.Enum.Type;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;


@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "owner")
public class Pet {
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private User owner;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    private String description;
    private LocalDate birthDate;
    private Integer price;

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet(User owner, String name, Type type, String description, LocalDate birthDate) {
        this.owner = owner;
        this.name = name;
        this.type = type;
        this.description = description;
        this.birthDate = birthDate;
    }
   public boolean hasOwner(){
        return owner != null;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer  price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Pet() {

    }
}
