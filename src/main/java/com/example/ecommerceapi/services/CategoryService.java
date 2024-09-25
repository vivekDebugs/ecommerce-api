package com.example.ecommerceapi.services;

import com.example.ecommerceapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
       this.categoryRepository = categoryRepository;
    }

    public void deleteCategory(long categoryId) {
        this.categoryRepository.deleteById(categoryId);
    }
}
