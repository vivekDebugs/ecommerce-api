package com.example.ecommerceapi.controllers;

import com.example.ecommerceapi.dtos.*;
import com.example.ecommerceapi.models.Product;
import com.example.ecommerceapi.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductService productService;

    @Value("${productService}")
    private String productServiceType;

    ProductController(@Qualifier("productServiceFakeStoreImpl") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProduct(@PathVariable long id) {
        Product product = this.productService.getProduct(id);
        return ProductResponseDTO.fromProduct(product);
    }

    @GetMapping("")
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> listOfProducts = this.productService.getAllProducts();
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
    public ProductResponseDTO updateProduct(@PathVariable long id, @RequestBody  ProductRequestDTO productRequestDTO) {
        Product product = this.productService.updateProduct(productRequestDTO.toProduct());
        return ProductResponseDTO.fromProduct(product);
    }

    @DeleteMapping("/{id}")
    public ProductResponseDTO deleteProduct(@PathVariable long id) {
        Product product = this.productService.deleteProduct(id);
        return ProductResponseDTO.fromProduct(product);
    }
}
