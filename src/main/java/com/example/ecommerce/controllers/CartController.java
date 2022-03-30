package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.AddToCartDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.services.CartService;
import com.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

//    @PostMapping("/add/{userId}")
//    public ResponseEntity<Cart> addToCart(@RequestBody AddToCartDto addToCartDto, @PathVariable Long userId) throws UserAlreadyExistException {
//        return cartService.addToCart(addToCartDto, userId);
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<CartCost> getCartItems(@PathVariable Long userId) throws UserAlreadyExistException{
//        ResponseEntity<CartCost> cartCost = cartService.listCartItems(userId);
//        return cartCost;
//    }
}
