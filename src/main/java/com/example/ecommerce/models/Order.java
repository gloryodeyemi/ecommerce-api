package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "checkout")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

//    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
//    private OrderDetails orderDetails;
    @OneToOne
    @JsonIgnoreProperties({"id", "user", "dateCreated", "dateUpdated"})
    private Cart cart;

//    @OneToOne
//    private UserAccount userId;
//
//    @OneToMany
//    private List<CartItems> orderItems;
//
//    private Double totalAmount;

    private OrderStatus orderStatus;

    @CreationTimestamp
    private Timestamp dateCreated;
    @UpdateTimestamp
    private  Timestamp dateUpdated;
}
