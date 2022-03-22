package com.example.ecommerce.dtos;

import com.example.ecommerce.models.Role;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class UserDto {
//    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private Role role;
    @NotNull
    private String password;
    private String passwordConfirm;
}
