package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.AddToCartDto;
import com.example.ecommerce.dtos.CheckoutDto;
import com.example.ecommerce.dtos.ViewOrderDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.CartItems;
import com.example.ecommerce.models.Order;
import com.example.ecommerce.services.OrderService;
import com.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
//    List<Order> orderList = new ArrayList<>();

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @PostMapping("/checkout")
    public ResponseEntity<Order> addToCart(@RequestBody CheckoutDto checkoutDto) throws UserAlreadyExistException {
        return orderService.userCheckout(checkoutDto);
    }

//    @GetMapping("/checkout/{merchantId}")
//    public ResponseEntity<Order> addToCart(@RequestBody ViewOrderDto viewOrderDto, @PathVariable Long merchantId) throws UserAlreadyExistException {
//        return orderService.viewOrder(viewOrderDto, merchantId);
//    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> viewOrderById(@PathVariable Long orderId) throws UserAlreadyExistException {
        return orderService.viewOrder(orderId);
    }
}
