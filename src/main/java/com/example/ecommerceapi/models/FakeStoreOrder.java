package com.example.ecommerceapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fakestore_order")
public class FakeStoreOrder extends Base {
    @ManyToOne
    private User user;

    @Column(name = "fakestore_product_id")
    private Long fakeStoreProductId;
    private Integer quantity;
}