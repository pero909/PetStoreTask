package com.example.petstore.Model.Exceptions;

public class PetNotFoundException extends RuntimeException{
    public PetNotFoundException(String message) {
        super(message);
    }
}
