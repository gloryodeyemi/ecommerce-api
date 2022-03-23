package com.example.ecommerce.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginDto {
    @NotNull
    private String emailAddress;
    @NotNull
    private String password;
}
