package com.example.ecommerce.services;

import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.ERole;
import com.example.ecommerce.models.Role;
import com.example.ecommerce.models.UserAccount;
import com.example.ecommerce.repositories.RoleRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public ResponseEntity<UserAccount> register(UserDto user) throws UserAlreadyExistException {
        if (userRepository.existsByEmail(user.getEmailAddress())) {
            throw new UserAlreadyExistException("Email address already exists!");
//            return ResponseEntity.badRequest().build();
        }
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(user, userAccount);

        // attaching user role
        Optional<Role> role = roleRepository.findByName(user.getRole());
        if (!role.isPresent()) {
            throw new UserAlreadyExistException("Role not found!");
        }
//        role.get().setName(role.get().getName());
        userAccount.setRole(role.get());

        // encoding the password
        String pass = passwordEncoder.encode(user.getPassword());
        String conPass = passwordEncoder.encode(user.getPasswordConfirm());
        if (pass.equals(conPass)) {
            userAccount.setPassword(pass);
            return ResponseEntity.ok(userRepository.save(userAccount));
        }
        throw new UserAlreadyExistException("Password does not match!");
    }

    public ResponseEntity login(UserDto loginCredentials) throws UserAlreadyExistException{
        Optional<UserAccount> user = userRepository.findByEmail(loginCredentials.getEmailAddress());
        if (user.isPresent()) {
            if (loginCredentials.getPassword().equals(user.get().getPassword())){
                return ResponseEntity.ok(user.get());
            }
            throw new UserAlreadyExistException("Incorrect password!");
        }
        return ResponseEntity.notFound().build();
    }


}
