package com.example.ecommerce.dtos;

import com.example.ecommerce.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDto {
        private Long id;
        private Long cartId;
        private Integer quantity;
        private Product product;
}
