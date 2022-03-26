package com.example.ecommerce.repositories;

import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findAllByCartId(Long cartId);

}