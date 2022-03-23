package com.example.ecommerce.services;

import com.example.ecommerce.dtos.RoleDto;
import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Role;
import com.example.ecommerce.models.UserAccount;
import com.example.ecommerce.repositories.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ResponseEntity<Role> createRole(RoleDto role) throws  UserAlreadyExistException{
        if (roleRepository.existsByName(role.getName())) {
            throw new UserAlreadyExistException("Role already exists!");
        }
        Role newRole = new Role();
        BeanUtils.copyProperties(role, newRole);
        return ResponseEntity.ok(roleRepository.save(newRole));
    }
}
