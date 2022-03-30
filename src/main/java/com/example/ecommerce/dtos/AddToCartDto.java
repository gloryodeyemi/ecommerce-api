package com.example.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddToCartDto {
    private Long id;
    private Long cartId;
    private Long productId;
    private Integer quantity;
}
