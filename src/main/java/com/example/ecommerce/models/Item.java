package com.example.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Product product;
    private Integer quantity;
    private Double amount = product.getPrice() * quantity;

    @ManyToOne
    private Cart cart;
}
