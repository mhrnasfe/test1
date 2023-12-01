package com.example.test.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopException extends RuntimeException{

    private final String message;




}
