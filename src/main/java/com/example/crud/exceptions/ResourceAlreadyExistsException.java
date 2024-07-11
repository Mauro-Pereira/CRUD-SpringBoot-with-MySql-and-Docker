package com.example.crud.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
