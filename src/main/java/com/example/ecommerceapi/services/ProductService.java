package com.example.ecommerceapi.services;

import com.example.ecommerceapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(long productId);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(long productId, Product product);
    Product deleteProduct(long productId);
}
