package com.example.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class UserAccount {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, unique = true, length = 45)
    private String emailAddress;

    @Column(nullable = false, unique = true, length = 45)
    private String phoneNumber;

    @ManyToOne
    @JsonIgnoreProperties({"id", "dateCreated", "dateUpdated"})
    private Role role;

    @OneToOne
    @JsonIgnoreProperties({"userId", "cartItems", "totalCost", "dateCreated", "dateUpdated"})
    private Cart cart;

    private String password;

//    @Transient
//    private String passwordConfirm;

    @CreationTimestamp
    private Timestamp dateCreated;
    @UpdateTimestamp
    private Timestamp dateUpdated;


}
