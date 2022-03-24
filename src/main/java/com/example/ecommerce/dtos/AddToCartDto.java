package com.example.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddToCartDto {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
}
