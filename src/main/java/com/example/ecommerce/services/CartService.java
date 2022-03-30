package com.example.ecommerce.services;

import com.example.ecommerce.dtos.AddToCartDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.UserAccount;
import com.example.ecommerce.repositories.CartItemRepository;
import com.example.ecommerce.repositories.CartRepository;
import com.example.ecommerce.repositories.ProductRepository;
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

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<CartItems> addToCart(AddToCartDto addToCartDto, Long userId) throws UserAlreadyExistException {
        // checking to see if user is registered
        Optional<UserAccount> user = userRepository.findById(userId);
        if (user.isPresent()) {
            // checking to see if user is a customer
            if (user.get().getRole().getName().equals("Customer")) {
                // checking to see if cart exists
                Optional<Cart> cart = cartRepository.findCartById(addToCartDto.getCartId());
                if (cart.isPresent()) {
                    // check if user cart id matches cart id
                    if (addToCartDto.getCartId().equals(user.get().getCart().getId())){
                        // attach user id to cart
//                        cart.get().setUserId(userId);
                        // add item to cart
                        CartItems cartItems = new CartItems();
                        BeanUtils.copyProperties(addToCartDto, cartItems);
                        cartItems.setCart(cart.get());
                        Optional<Product> product = productRepository.findProductById(addToCartDto.getProductId());
                        if (product.isPresent()){
                            cartItems.setProduct(product.get());
                            cartItemRepository.save(cartItems);
//                        cart.get().getCartItems().add(cartItems);
                            return ResponseEntity.ok(cartItems);
                        }
                        return ResponseEntity.notFound().build();
                    }
                    throw new UserAlreadyExistException("Action not allowed");
                }
                throw new UserAlreadyExistException("Cart not found");
            }
            throw new UserAlreadyExistException("Action not allowed!");
        }
        throw new UserAlreadyExistException("Unauthorized!");
    }

    public ResponseEntity<Cart> getCartByUserId(Long userId) {
        Optional<Cart> cart = cartRepository.findCartByUserId(userId);
        if (cart.isPresent()){
            Double sum = 0D;
            List<CartItems> cartItemsList = cart.get().getCartItems();
            if (!(cartItemsList == null)){
                for (CartItems cartIt: cartItemsList){
                    sum += (cartIt.getProduct().getPrice() * cartIt.getQuantity());
                }
            }
            cart.get().setTotalCost(sum);
            return ResponseEntity.ok(cart.get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Cart> getCartById(Long id) {
        Optional<Cart> cart = cartRepository.findCartById(id);
        if (cart.isPresent()){
            Double sum = 0D;
            List<CartItems> cartItemsList = cart.get().getCartItems();
            if (!(cartItemsList == null)){
                for (CartItems cartIt: cartItemsList){
                    sum += (cartIt.getProduct().getPrice() * cartIt.getQuantity());
                }
            }
            cart.get().setTotalCost(sum);
            return ResponseEntity.ok(cart.get());
        }
        return ResponseEntity.notFound().build();
    }
}
