package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Cart cart;

    @OneToOne
    private Product product;
    private Integer quantity;

    @CreationTimestamp
    private Timestamp dateCreated;
    @UpdateTimestamp
    private Timestamp dateUpdated;
}
