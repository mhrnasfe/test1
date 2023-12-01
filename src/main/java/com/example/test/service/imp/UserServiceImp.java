package com.example.test.service.imp;

import com.example.test.dto.UserDto;
import com.example.test.entity.Product;
import com.example.test.entity.User;
import com.example.test.exeption.AllReadyExist;
import com.example.test.exeption.ProductNotFound;
import com.example.test.repository.UserRepository;
import com.example.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public User addUser(UserDto userDto) throws AllReadyExist {
        Optional<User> user = userRepository
                .findByMobile(userDto.getMobileNo());
        if (user.isPresent()) {
            throw new AllReadyExist("this product is present");
        } else {
            User savedUser = new User();
            savedUser.setMobile(userDto.getMobileNo());
            savedUser.setPassword(userDto.getPassword());
            return userRepository.save(savedUser);
        }
    }

    @Override
    public List<User> all() {
        ArrayList<Product> product = new ArrayList<>();
        return userRepository.findAll();

    }

    @Override
    public User getUserById(Long id) {
        Optional<User> byId = userRepository.findById(id) ;
        if (byId.isPresent()){
            return byId.get();
        }else{
            throw new ProductNotFound("productId not found");
        }

    }


    @Override
    public User updateUser(UserDto userDto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("productNotFound"));
        user.setMobile(userDto.getMobileNo());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("productId not found"));
        userRepository.delete(user);
    }



}
