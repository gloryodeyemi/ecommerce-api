package com.example.ecommerce.services;

import com.example.ecommerce.dtos.CheckoutDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.*;
import com.example.ecommerce.repositories.CartRepository;
import com.example.ecommerce.repositories.OrderRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Order> userCheckout(CheckoutDto checkoutDto) throws UserAlreadyExistException {
        // checking to see if user is registered
        Optional<UserAccount> user = userRepository.findById(checkoutDto.getUserId());
        if (user.isPresent()) {
            // checking to see if user is a customer
            if (user.get().getRole().getName().equals("Customer")) {
                // checking to see if cart exists
                Optional<Cart> cart = cartRepository.findCartById(checkoutDto.getCartId());
                if (cart.isPresent()) {
                    // check if user cart id matches cart id
                    if (cart.get().getId().equals(user.get().getCart().getId())){
                        // checkout items in cart
                        Order newOrder = new Order();
                        newOrder.setCart(cart.get().getUser().getCart());
                        newOrder.setOrderStatus(OrderStatus.ON_HOLD);
                        orderRepository.save(newOrder);
//                        cart.get().setCartItems(null);
                        cart.get().getCartItems().clear();
                        return ResponseEntity.ok(newOrder);
                    }
                    throw new UserAlreadyExistException("Action not allowed");
                }
                throw new UserAlreadyExistException("Cart not found");
            }
            throw new UserAlreadyExistException("Action not allowed!");
        }
        throw new UserAlreadyExistException("Unauthorized!");
    }
}