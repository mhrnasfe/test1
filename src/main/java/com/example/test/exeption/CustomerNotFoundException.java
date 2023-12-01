package com.example.test.exeption;

public class CustomerNotFoundException extends ShopException{

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
