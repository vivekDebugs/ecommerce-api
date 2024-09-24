package com.example.ecommerceapi.dtos;

import com.example.ecommerceapi.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductRequestDTO {
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public static FakeStoreProductRequestDTO fromProduct(Product product) {
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO = new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setTitle(product.getName());
        fakeStoreProductRequestDTO.setDescription(product.getDescription());
        fakeStoreProductRequestDTO.setPrice(product.getPrice());
        fakeStoreProductRequestDTO.setImage(product.getImageUrl());
        fakeStoreProductRequestDTO.setCategory(product.getCategory().getName());

        return fakeStoreProductRequestDTO;
    }
}
