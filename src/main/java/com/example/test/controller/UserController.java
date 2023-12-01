package com.example.test.controller;

import com.example.test.dto.UserDto;
import com.example.test.entity.User;
import com.example.test.exeption.AllReadyExist;
import com.example.test.service.imp.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserServiceImp userService;

    //    @GetMapping("/home")
//    public ModelAndView home() {
//        return new ModelAndView("home");
//    }
//
//    @GetMapping("/login")
//    public ModelAndView login(){
//        ModelAndView view = new ModelAndView();
//        view.addObject("loginMessage", "Please enter your credentials");
//        view.setViewName("login");
//        return view;
//    }
    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserDto userDto) throws AllReadyExist {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.all());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getProductById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateProduct(@RequestBody UserDto userDto, @PathVariable Long id) {
        User updateUser = userService.updateUser(userDto, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

}
