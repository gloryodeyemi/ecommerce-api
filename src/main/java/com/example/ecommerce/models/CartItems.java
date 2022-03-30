package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class CartItems {
    @Id
    @GeneratedValue
    private Long id;

//    private Long cartId;

    @ManyToOne
    @JsonIgnoreProperties({"userId", "cartItems", "totalCost", "dateCreated", "dateUpdated"})
    private Cart cart;

    @OneToOne
    @JsonIgnoreProperties({"id", "productDescription", "productCategory", "dateCreated", "dateUpdated"})
    private Product product;
    private Integer quantity;

    @CreationTimestamp
    private Timestamp dateCreated;
    @UpdateTimestamp
    private Timestamp dateUpdated;
}
