package com.example.ecommerce.services;

import com.example.ecommerce.dtos.LoginDto;
import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.ERole;
import com.example.ecommerce.models.Role;
import com.example.ecommerce.models.UserAccount;
import com.example.ecommerce.repositories.CartRepository;
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

    @Autowired
    CartRepository cartRepository;

    public ResponseEntity<UserAccount> register(UserDto user) throws UserAlreadyExistException {
        // checking to see if user already exists
        if (userRepository.existsByEmailAddress(user.getEmailAddress())) {
            throw new UserAlreadyExistException("Email address already exists!");
        }
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(user, userAccount);

        // attaching user role
        Optional<Role> role = roleRepository.findById(user.getRole());
        if (!role.isPresent()) {
            throw new UserAlreadyExistException("Role not found!");
        }
        userAccount.setRole(role.get());

        // encoding the password
        String pass = passwordEncoder.encode(user.getPassword());
        if (user.getPassword().equals(user.getPasswordConfirm())) {
            userAccount.setPassword(pass);
            // saving the user
            UserAccount acc = userRepository.save(userAccount);
            // attaching cart
            if (role.get().getName().equals("Customer")) {
                Cart cart = new Cart();
                cart.setUser(acc);
                cartRepository.save(cart);
//            System.out.println(cart);

//            System.out.println(cart);
            }
//            // attaching a cart if user is a customer
//            if (role.get().getName().equals("Customer")){
//                cart.setUserId(userAccount.getId());
//            }
            return ResponseEntity.ok(userAccount);
        }
        throw new UserAlreadyExistException("Password does not match!");
    }

    public ResponseEntity login(LoginDto loginCredentials) throws UserAlreadyExistException{
        Optional<UserAccount> user = userRepository.findByEmailAddress(loginCredentials.getEmailAddress());
        if (user.isPresent()) {
            if (passwordEncoder.matches(loginCredentials.getPassword(), user.get().getPassword())){
                return ResponseEntity.ok(user.get());
            }
            throw new UserAlreadyExistException("Incorrect password!");
        }
        return ResponseEntity.notFound().build();
    }


}
