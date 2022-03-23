package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.ProductDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    List<Product> productList = new ArrayList<>();

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto, @PathVariable Long userId) throws UserAlreadyExistException {
        return productService.createProduct(productDto, userId);
    }
}
