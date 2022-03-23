package com.example.ecommerce.dtos;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
        private Long id;
        @NotNull
        private String name;
}
