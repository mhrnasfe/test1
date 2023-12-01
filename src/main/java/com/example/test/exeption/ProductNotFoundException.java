package com.example.test.exeption;

public class ProductNotFoundException extends ShopException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
