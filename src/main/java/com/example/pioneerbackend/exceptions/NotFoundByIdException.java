package com.example.pioneerbackend.exceptions;

public class NotFoundByIdException extends RuntimeException {
    public <T> NotFoundByIdException(Class<T> clazz, Long id){
        super(clazz.getName() + " with id = " + id + " not found");
    }
}