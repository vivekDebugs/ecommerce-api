package com.example.ecommerceapi.repositories;

import com.example.ecommerceapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    Category save(Category category);

    void deleteById(long id);

    Optional<Category> findByName(String name);
}
