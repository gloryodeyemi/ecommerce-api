package com.example.ecommerce.dtos;

import com.example.ecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartDto {
    private Long id;
    private Long userId;
    private Integer quantity;
//    private Long product;
    private Product product;
}
