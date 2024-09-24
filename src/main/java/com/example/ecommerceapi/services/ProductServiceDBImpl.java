package com.example.ecommerceapi.services;

import com.example.ecommerceapi.models.Category;
import com.example.ecommerceapi.models.Product;
import com.example.ecommerceapi.repositories.CategoryRepository;
import com.example.ecommerceapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("productServiceDBImpl")
public class ProductServiceDBImpl implements ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductServiceDBImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProduct(long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        Category category;
        if (categoryOptional.isPresent()) {
            category = categoryOptional.get();
        } else {
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            category = newCategory;
            categoryRepository.save(category);
        }

        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long productId, Product product) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
            Category category;
            if (categoryOptional.isPresent()) {
                category = categoryOptional.get();
            } else {
                Category newCategory = new Category();
                newCategory.setName(product.getCategory().getName());
                category = newCategory;
                categoryRepository.save(category);
            }
            product.setCategory(category);
        } else {
            return null;
        }
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(long productId) {
        Product product = productRepository.findById(productId).get();
        productRepository.delete(product);
        return product;
    }
}
