package com.example.test.service.imp;

import com.example.test.Mapper;
import com.example.test.entity.Address;
import com.example.test.exeption.AddressNotFound;
import com.example.test.repository.AddressRepository;
import com.example.test.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressRepository addressRepository;
    private final Mapper mapper;

    @Override
    public Address addAddress(Address address) {
        Optional<Address> address1 = addressRepository
                .findByCity(address.getCity());
        if (address1.isPresent()) {
            throw new AddressNotFound("this address is present");
        } else {
            Address save= addressRepository.save(mapper.addAddress(address));
            return mapper.addAddress(save);
        }
    }

    @Override
    public List<Address> all() {
        ArrayList<Address> addresses = new ArrayList<>();
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return null;
    }

    @Override
    public Address updateAddress(Address address, Long id) {
        Address address1 = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFound("address not found"));
        address1.setCity(address.getCity());
        address1.setCountry(address.getCountry());
        address1.setPostalCode(address.getPostalCode());
        address1.setStreet(address.getStreet());
        address1.setState(address.getState());
        return addressRepository.save(address);    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFound("address not found"));
        addressRepository.delete(address);
    }
}
