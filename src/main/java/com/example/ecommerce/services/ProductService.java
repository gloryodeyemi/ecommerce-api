package com.example.ecommerce.services;

import com.example.ecommerce.dtos.ProductDto;
import com.example.ecommerce.dtos.UserDto;
import com.example.ecommerce.exceptions.UserAlreadyExistException;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.Role;
import com.example.ecommerce.models.UserAccount;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.repositories.ProductRepository;
import com.example.ecommerce.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity<Product> createProduct(ProductDto productDto, Long userId) throws UserAlreadyExistException {
        // checking to see if user is registered
        Optional<UserAccount> user = userRepository.findById(userId);
        if (user.isPresent()) {
            // checking to see if user is a merchant
            if (user.get().getRole().getName().equals("Merchant")) {
                // create a new product object
                Product product = new Product();
                BeanUtils.copyProperties(productDto, product);
                // attaching category
                Optional<Category> category = categoryRepository.findCategoryById(productDto.getProductCategory());
                if (!category.isPresent()) {
                    throw new UserAlreadyExistException("Category not found!");
                }
                product.setProductCategory(category.get());
                return ResponseEntity.ok(productRepository.save(product));
            }
            throw new UserAlreadyExistException("Action not allowed!");
        }
        throw new UserAlreadyExistException("Unauthorized!");
    }

    public Product getProductById(Long productId) throws UserAlreadyExistException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent())
            throw new UserAlreadyExistException("Product id is invalid " + productId);
        return optionalProduct.get();
    }
}
