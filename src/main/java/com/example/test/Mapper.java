package com.example.test;

import com.example.test.entity.Address;
import lombok.Data;

@Data
public class Mapper {

    public Address addAddress(Address address) {
        Address savedAddress = new Address();
        savedAddress.setCity(address.getCity());
        savedAddress.setCountry(address.getCountry());
        savedAddress.setStreet(address.getStreet());
        savedAddress.setPostalCode(address.getPostalCode());
        return savedAddress;
    }
}
