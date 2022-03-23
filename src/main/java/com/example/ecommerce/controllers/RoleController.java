package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.RoleDto;
import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Role;
import com.example.ecommerce.models.UserAccount;
import com.example.ecommerce.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    List<Role> roleList = new ArrayList<>();

    @Autowired
    RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDto role) throws UserAlreadyExistException {
        return roleService.createRole(role);
    }
}
