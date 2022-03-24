package com.example.ecommerce.models;

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
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Long cartId;

    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails;

    private Double totalPrice;

//    private String orderNum;
//    private String status;
//
//    @Column(nullable = false)
//    private String customerFirstName;
//
//    @Column(nullable = false)
//    private String customerLastName;
//
//    @Column(length = 255, nullable = false)
//    private String customerAddress;
//
//    @Column(nullable = false)
//    private String customerEmail;
//
//    @Column(nullable = false)
//    private String customerPhone;

    @CreationTimestamp
    private Timestamp dateCreated;

    @UpdateTimestamp
    private  Timestamp dateUpdated;
}
