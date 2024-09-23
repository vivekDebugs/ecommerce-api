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
        ProductResponseDTO productDTO = new ProductResponseDTO();
        productDTO.id = product.getId();
        productDTO.name = product.getName();
        productDTO.description = product.getDescription();
        productDTO.price = product.getPrice();
        productDTO.imageUrl = product.getImageUrl();
        productDTO.category = product.getCategory();
        return productDTO;
    }
}
