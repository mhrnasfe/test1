package com.example.test.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ProductDto {

    @NotNull(message = "please input a valid product name")
    @Pattern(regexp = "(?i)(^[a-z]+)[a-z .,-]((?! .,-)$){1,25}$",message = "please input a valid product name")
    private String productName;
    @NotNull(message = "please input a valid product name")
    private Double price;
}
