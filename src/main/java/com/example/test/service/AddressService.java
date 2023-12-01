package com.example.test.service;

import com.example.test.entity.Address;

import java.util.List;

public interface AddressService {
    Address addAddress(Address address);

    List<Address> all();

    Address getAddressById(Long id);

    Address updateAddress(Address customer, Long id);

    void deleteAddress(Long id);

}
