package com.example.ecommerce.models;

import com.example.ecommerce.dtos.OrderDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

//@Entity
@Getter @Setter
//@Table(name = "order")
public class Order {

//    private Long cartId;

//    @OneToMany(mappedBy = "order")
    private List<OrderDto> orderDetails;

    private Double totalPrice;
//    private OrderStatus status;

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

//    @CreationTimestamp
//    private Timestamp dateCreated;
//
//    @UpdateTimestamp
//    private  Timestamp dateUpdated;
}
