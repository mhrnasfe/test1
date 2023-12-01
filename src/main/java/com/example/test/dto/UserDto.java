package com.example.test.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {

    @NotNull(message = "Enter Your Mobile Number")
    @Pattern(regexp = "[09]{2}[0-9]{9}", message = "Input a valid mobile number")
    private String mobileNo;
    @NotNull(message = "Enter Your Password")
    private String password;
}
