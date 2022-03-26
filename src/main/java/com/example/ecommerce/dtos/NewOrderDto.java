package com.example.ecommerce.dtos;

import com.example.ecommerce.models.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewOrderDto {
    private Long id;
    private Long cartId;
//    private OrderStatus status;
}
