package com.example.ecommerceapi.dtos;

import com.example.ecommerceapi.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResponseDTO {
    private long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public Product toProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.image);
        product.setCategory(this.category);
        return product;
    }
}
