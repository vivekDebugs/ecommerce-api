package com.example.ecommerceapi.controllers;

import com.example.ecommerceapi.dtos.ProductResponseDTO;
import com.example.ecommerceapi.models.Product;
import com.example.ecommerceapi.services.ProductServiceFakeStoreImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fakestore/products")
public class FakeStoreProductController {
    ProductServiceFakeStoreImpl productService;

    FakeStoreProductController(ProductServiceFakeStoreImpl productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> listOfProductResponseDTOs = new ArrayList<>();
        List<Product> listOfProducts = this.productService.getAllProducts();
        for (Product product : listOfProducts) {
            listOfProductResponseDTOs.add(ProductResponseDTO.fromProduct(product));
        }
        return listOfProductResponseDTOs;
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProduct(@PathVariable Long id) {
        Product product = this.productService.getProduct(id);
        return ProductResponseDTO.fromProduct(product);
    }
}
