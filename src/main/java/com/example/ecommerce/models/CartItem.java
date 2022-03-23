package com.example.ecommerce.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @NotNull
    private Product iProduct;
    @NotNull
    private Integer productQuantity;
//    @NotNull
//    private Double productAmount = iProduct.getPrice() * productQuantity;
    public Double productAmount() {
        Double sum = 0D;
        sum += iProduct.getPrice() * productQuantity;
        return sum;
    }

    @ManyToOne
    private Cart cart;
}
