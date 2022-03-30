package com.example.ecommerce.controllers;

import com.example.ecommerce.models.Order;
import com.example.ecommerce.services.OrderService;
import com.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    List<Order> orderList = new ArrayList<>();

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;
}
