package com.example.ecommerceapi.dtos;

import com.example.ecommerceapi.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private String category;

    public static ProductResponseDTO fromProduct(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.id = product.getId();
        productResponseDTO.name = product.getName();
        productResponseDTO.description = product.getDescription();
        productResponseDTO.price = product.getPrice();
        productResponseDTO.imageUrl = product.getImageUrl();
        productResponseDTO.category = product.getCategory().getName();

        return productResponseDTO;
    }
}
