package com.example.test.exeption;

public class ProductNotFound extends ShopException{
    public ProductNotFound(String message) {
        super(message);
    }
}
