package com.example.ecommerce.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoleDto {
    private Long id;
    @NotNull
    private String name;
}
