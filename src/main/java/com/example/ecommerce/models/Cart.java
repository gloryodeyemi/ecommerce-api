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
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

//    @OneToOne
//    @JsonIgnoreProperties({"firstName", "lastName", "emailAddress", "phoneNumber", "role", "cart", "password", "dateCreated", "dateUpdated"})
//    private UserAccount user;
    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"cart", "dateCreated", "dateUpdated"})
    private List<CartItems> cartItems;

    private Double totalCost;

    @CreationTimestamp
    private Timestamp dateCreated;

    @UpdateTimestamp
    private Timestamp dateUpdated;

}
