package com.example.ecommerce.dtos;

import com.example.ecommerce.models.Category;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDto {
    private Long id;
    @NotNull
    private String productName;
    private String productDescription;
    @NotNull
    private Double price;
    private String pictureUrl;
    @NotNull
    private Long productCategory;
}
