package com.example.ecommerce.dtos;

import com.example.ecommerce.models.ERole;
import com.example.ecommerce.models.Role;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class UserDto {
//    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String emailAddress;
    @NotNull
    private String phoneNumber;
    @NotNull
    private ERole role;
    @NotNull
    private String password;
    @NotNull
    private String passwordConfirm;
}
