package com.example.ecommerceapi.dtos;

import com.example.ecommerceapi.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private String category;

    public Product toProduct() {
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        product.setCategory(this.category);
        return product;
    }
}
