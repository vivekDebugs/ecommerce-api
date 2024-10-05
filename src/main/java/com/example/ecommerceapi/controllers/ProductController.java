package com.example.ecommerceapi.controllers;

import com.example.ecommerceapi.dtos.*;
import com.example.ecommerceapi.models.Product;
import com.example.ecommerceapi.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @Value("${productService}")
    private String productServiceType;

    ProductController(@Qualifier("productServiceDBImpl") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProduct(@PathVariable Long id) {
        Product product = this.productService.getProduct(id);
        return ProductResponseDTO.fromProduct(product);
    }

    @GetMapping("")
    public List<ProductResponseDTO> getAllProducts(@RequestParam("category") Optional<String> categoryOptional) {
        List<Product> listOfProducts;
        if (categoryOptional.isEmpty()) {
            listOfProducts = this.productService.getAllProducts();
        } else {
            listOfProducts = this.productService.getAllProductsByCategory(categoryOptional.get());
        }
        List<ProductResponseDTO> listOfProductResponseDTOs = new ArrayList<>();
        for (Product product: listOfProducts) {
            listOfProductResponseDTOs.add(ProductResponseDTO.fromProduct(product));
        }
        return listOfProductResponseDTOs;
    }

    @PostMapping("")
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        Product product = this.productService.createProduct(productRequestDTO.toProduct());
        return ProductResponseDTO.fromProduct(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id, @RequestBody  ProductRequestDTO productRequestDTO) {
        Product product = this.productService.updateProduct(id, productRequestDTO.toProduct());
        return ProductResponseDTO.fromProduct(product);
    }

    @DeleteMapping("/{id}")
    public ProductResponseDTO deleteProduct(@PathVariable Long id) {
        Product product = this.productService.deleteProduct(id);
        return ProductResponseDTO.fromProduct(product);
    }
}
