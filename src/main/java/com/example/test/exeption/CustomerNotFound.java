package com.example.test.exeption;

public class CustomerNotFound extends ShopException{

    public CustomerNotFound(String message) {
        super(message);
    }
}
