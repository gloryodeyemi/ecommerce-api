package com.example.ecommerce.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class OrderDetails {
    @Id
    @GeneratedValue
    private Long id;

    private Product products;
}
