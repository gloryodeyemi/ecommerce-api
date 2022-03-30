package com.example.ecommerce.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutDto {
    private Long id;
    private Long cartId;
    private Long userId;
}
