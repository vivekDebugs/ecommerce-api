package com.example.ecommerceapi.repositories;

public interface CustomQueries {
    String GET_ALL_PRODUCTS_BY_CATEGORY =
            "SELECT p.* " +
            "FROM product p " +
            "LEFT JOIN category c " +
            "ON p.category_id = c.id " +
            "WHERE c.name = :category";
}
