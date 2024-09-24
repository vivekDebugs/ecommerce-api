package com.example.ecommerceapi.repositories;

import com.example.ecommerceapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    Category save(Category category);

    @Override
    void delete(Category category);

    Optional<Category> findByName(String name);
}
