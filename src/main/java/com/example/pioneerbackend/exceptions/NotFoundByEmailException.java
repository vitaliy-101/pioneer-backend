package com.example.pioneerbackend.exceptions;

public class NotFoundByEmailException extends RuntimeException {
    public <T> NotFoundByEmailException(Class<T> clazz, String email){
        super("User with email = " + email + " not found");
    }
}