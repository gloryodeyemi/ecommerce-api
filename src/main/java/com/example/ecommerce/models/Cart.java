package com.example.ecommerce.models;

import com.sun.istack.NotNull;
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
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

//    @OneToOne
//    private UserAccount userAccount;

    private Long userId;
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;

    private int quantity;

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    private Set<CartItem> cartItems;
//
//    @Transient
//    public Double totalAmount() {
//        Double sum = 0D;
//        for (CartItem it : cartItems) {
//            sum += it.productAmount();
//        }
//        return sum;
//    }

    @CreationTimestamp
    private Timestamp dateCreated;

    @UpdateTimestamp
    private Timestamp dateUpdated;

}
