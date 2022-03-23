package com.example.ecommerce.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter @Setter
public class Category {

    @Id
    @GeneratedValue
    private Long id;

//    private enum names{
//        CATEGORY_ELECTRONICS,
//        CATEGORY_FASHION,
//        CATEGORY_STATIONARY,
//        CATEGORY_FURNITURE
//    }
    private String name;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private Set<Product> products;

    @CreationTimestamp
    private Timestamp dateCreated;

}
