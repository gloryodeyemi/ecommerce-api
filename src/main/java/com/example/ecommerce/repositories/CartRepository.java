package com.example.ecommerce.repositories;

import com.example.ecommerce.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
//    List<Cart> findAllByUserId(Long userId);
    Cart findCartByUserId(Long userId);

}
