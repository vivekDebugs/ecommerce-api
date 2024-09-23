package com.example.ecommerceapi.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private String category;
}
