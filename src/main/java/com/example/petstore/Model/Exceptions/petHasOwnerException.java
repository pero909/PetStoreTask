package com.example.petstore.Model.Exceptions;

public class petHasOwnerException extends RuntimeException{
    public petHasOwnerException(String message) {
        super(message);
    }
}
