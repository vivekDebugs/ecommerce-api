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
        FakeStoreProductRequestDTO createProductFakeStoreImplRequestDTO = new FakeStoreProductRequestDTO();
        createProductFakeStoreImplRequestDTO.setTitle(product.getName());
        createProductFakeStoreImplRequestDTO.setDescription(product.getDescription());
        createProductFakeStoreImplRequestDTO.setPrice(product.getPrice());
        createProductFakeStoreImplRequestDTO.setImage(product.getImageUrl());
        createProductFakeStoreImplRequestDTO.setCategory(product.getCategory());
        return createProductFakeStoreImplRequestDTO;
    }
}
