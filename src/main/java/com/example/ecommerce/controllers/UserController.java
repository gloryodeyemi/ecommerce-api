package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.LoginDto;
import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.UserAccount;
import com.example.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserAccount> registerUser(@RequestBody UserDto user) throws UserAlreadyExistException {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAccount> userLogin(@RequestBody LoginDto cred) throws UserAlreadyExistException {
        return userService.login(cred);
    }
}
