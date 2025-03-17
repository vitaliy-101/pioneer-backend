package com.example.pioneerbackend.exceptions;

public class NotFoundUserIdForBasket extends RuntimeException {
    public <T> NotFoundUserIdForBasket(){
        super("User id not found for saving item to basket");
    }
}
