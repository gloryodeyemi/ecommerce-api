package com.example.ecommerce.models;

import com.example.ecommerce.dtos.CartDto;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
public class CartCost {
    private List<CartDto> cartItems;
    private Double totalCost;
}
