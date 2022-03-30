package com.example.ecommerce.repositories;
//
//import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.Order;
//import com.example.ecommerce.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//
import java.util.List;
import java.util.Optional;

//
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    Optional<Order> findOrderByUserId(Long userId);
}
