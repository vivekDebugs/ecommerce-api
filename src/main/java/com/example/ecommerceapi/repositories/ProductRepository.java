package com.example.ecommerceapi.repositories;

import com.example.ecommerceapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Product save(Product product);

    @Override
    void delete(Product product);

    Optional<Product> findById(long id);

    List<Product> findByCategory_name(String category);

    @Query("select p from Product p where p.category.name = :category")
    List<Product> findByCategory_JPAQuery(@Param("category") String category);

    @Query(value = CustomQueries.GET_ALL_PRODUCTS_BY_CATEGORY, nativeQuery = true)
    List<Product> findByCategory_NativeQuery(@Param("category") String category);
}
