package com.example.test.service;

import com.example.test.dto.UserDto;
import com.example.test.entity.User;
import com.example.test.exeption.AllReadyExist;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    User addUser(UserDto userDto) throws AllReadyExist;

    List<User> all();

    User getUserById(Long id);

    User updateUser(UserDto userDto, Long id);

    void deleteUser(Long id);
}
