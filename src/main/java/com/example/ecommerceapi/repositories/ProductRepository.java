package com.example.ecommerceapi.repositories;

import com.example.ecommerceapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Product save(Product product);

    @Override
    void delete(Product product);

    Optional<Product> findById(long id);
}
