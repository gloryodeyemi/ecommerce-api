package com.example.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter @Setter
public class Role {

    @Id
    @GeneratedValue
    private Long id;

//    private ERole name;
    private String name;

//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    private Set<UserAccount> userAccount;

    @CreationTimestamp
    private Timestamp dateCreated;

    @UpdateTimestamp
    private Timestamp dateUpdated;

}
