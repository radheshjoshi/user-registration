package com.example.user_registration.controller;

import com.example.user_registration.dto.UserDto;
import com.example.user_registration.entity.User;
import com.example.user_registration.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/home")
    public String homePage(@RequestParam(value = "username", defaultValue = "User") String username){
        return String.format("Hello %s", username);
    }
}
