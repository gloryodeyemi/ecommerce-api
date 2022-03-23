package com.example.ecommerce.services;

import com.example.ecommerce.dtos.CategoryDto;
import com.example.ecommerce.dtos.RoleDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.Role;
import com.example.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity<Category> createCategory(CategoryDto category) throws UserAlreadyExistException {
        if (categoryRepository.existsByName(category.getName())) {
            throw new UserAlreadyExistException("Category already exists!");
        }
        Category newCategory = new Category();
        BeanUtils.copyProperties(category, newCategory);
        return ResponseEntity.ok(categoryRepository.save(newCategory));
    }
}
