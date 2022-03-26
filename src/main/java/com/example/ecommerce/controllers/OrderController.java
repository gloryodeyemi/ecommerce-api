package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.AddToCartDto;
import com.example.ecommerce.dtos.NewOrderDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.CartCost;
import com.example.ecommerce.models.Order;
import com.example.ecommerce.models.OrderDetails;
import com.example.ecommerce.services.CartService;
import com.example.ecommerce.services.OrderService;
import com.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

//    @Autowired
//    private ProductService productService;

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<OrderDetails> checkout(@RequestBody NewOrderDto newOrderDto, @PathVariable Long userId) throws UserAlreadyExistException {
        return orderService.newOrder(newOrderDto, userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Order> viewOrder(@PathVariable Long userId) throws UserAlreadyExistException{
        ResponseEntity<Order> order = orderService.getOrderDetails(userId);
        return order;
    }
}
