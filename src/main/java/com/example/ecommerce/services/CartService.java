package com.example.ecommerce.services;

import com.example.ecommerce.dtos.AddToCartDto;
import com.example.ecommerce.dtos.CartDto;
import com.example.ecommerce.dtos.CategoryDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.*;
import com.example.ecommerce.repositories.CartRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Cart> addToCart(AddToCartDto addToCartDto, Long userId) throws UserAlreadyExistException {
        // checking to see if user is registered
        Optional<UserAccount> user = userRepository.findById(userId);
        if (user.isPresent()) {
            // checking to see if user is a customer
            if (user.get().getRole().getName().equals("Customer")) {
                // create a new cart object
                Cart cart = new Cart();
                BeanUtils.copyProperties(addToCartDto, cart);
                cart.setUserId(userId);
                return ResponseEntity.ok(cartRepository.save(cart));
            }
            throw new UserAlreadyExistException("Action not allowed!");
        }
        throw new UserAlreadyExistException("Unauthorized!");
    }

    public ResponseEntity<CartCost> listCartItems(Long userId) {
       List<Cart> cartList = cartRepository.findAllByUserId(userId);
       List<CartDto> cartItems = new ArrayList<>();
       for (Cart cart: cartList){
            CartDto cartDto = getDtoFromCart(cart);
            cartItems.add(cartDto);
       }
       Double totalCost = 0D;
       for (CartDto cartDto:cartItems){
           totalCost += (cartDto.getProduct().getPrice() * cartDto.getQuantity());
       }
       CartCost cartCost = new CartCost();
       cartCost.setCartItems(cartItems);
       cartCost.setTotalCost(totalCost);
       return ResponseEntity.ok(cartCost);
    }

    public static CartDto getDtoFromCart(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setUserId(cart.getUserId());
        cartDto.setProduct(cart.getProduct());
        cartDto.setQuantity(cart.getQuantity());
        return cartDto;
    }
}
