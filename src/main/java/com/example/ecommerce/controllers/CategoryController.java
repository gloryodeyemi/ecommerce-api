package com.example.ecommerce.controllers;

import com.example.ecommerce.dtos.CategoryDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    List<Category> categoryList = new ArrayList<>();

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) throws UserAlreadyExistException {
        return categoryService.createCategory(categoryDto);
    }
}
