package com.example.ecommerce.services;

import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.UserAccount;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<UserAccount> register(UserDto user) throws UserAlreadyExistException {
        if (userRepository.existsByEmail(user.getEmailAddress())) {
            throw new UserAlreadyExistException("Email address already exists");
//            return ResponseEntity.badRequest().build();
        }
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(user, userAccount);
        String pass = passwordEncoder.encode(user.getPassword());
        String conPass = passwordEncoder.encode(user.getPasswordConfirm());
        if (pass.equals(conPass)) {
            userAccount.setPassword(pass);
            return ResponseEntity.ok(userRepository.save(userAccount));
        }
        throw new UserAlreadyExistException("Password does not match");
    }


}
