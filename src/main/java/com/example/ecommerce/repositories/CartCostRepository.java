package com.example.ecommerce.repositories;

import com.example.ecommerce.models.CartCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartCostRepository extends JpaRepository<CartCost, Long> {
}
