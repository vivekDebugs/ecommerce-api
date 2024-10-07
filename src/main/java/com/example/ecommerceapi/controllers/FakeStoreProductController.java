package com.example.ecommerceapi.controllers;

import com.example.ecommerceapi.dtos.ProductResponseDTO;
import com.example.ecommerceapi.models.Product;
import com.example.ecommerceapi.services.AuthService;
import com.example.ecommerceapi.services.ProductServiceFakeStoreImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    AuthService authService;

    FakeStoreProductController(ProductServiceFakeStoreImpl productService, AuthService authService) {
        this.productService = productService;
        this.authService = authService;
    }

    @GetMapping("")
    public ResponseEntity getAllProducts() {
        if (!this.authService.fakeValidate()) {
            return new ResponseEntity<>("User not authenticated", HttpStatus.FORBIDDEN);
        }

        List<ProductResponseDTO> listOfProductResponseDTOs = new ArrayList<>();
        List<Product> listOfProducts = this.productService.getAllProducts();
        for (Product product : listOfProducts) {
            listOfProductResponseDTOs.add(ProductResponseDTO.fromProduct(product));
        }
        return new ResponseEntity<>(listOfProductResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        if (!this.authService.fakeValidate()) {
            return new ResponseEntity<>("User not authenticated", HttpStatus.FORBIDDEN);
        }
        Product product = this.productService.getProduct(id);
        return new ResponseEntity(ProductResponseDTO.fromProduct(product), HttpStatus.OK);
    }
}
