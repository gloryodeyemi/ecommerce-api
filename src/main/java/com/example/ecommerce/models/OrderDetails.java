package com.example.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class OrderDetails {
    @Id
    @GeneratedValue
    private Long id;

    private Long cartId;
    private OrderStatus status;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;

    @ManyToOne
    private Product product;

    private Long quantity;

    @CreationTimestamp
    private Timestamp dateCreated;

    @UpdateTimestamp
    private  Timestamp dateUpdated;
}
