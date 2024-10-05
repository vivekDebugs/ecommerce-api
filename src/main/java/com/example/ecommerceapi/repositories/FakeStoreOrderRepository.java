package com.example.ecommerceapi.repositories;

import com.example.ecommerceapi.models.FakeStoreOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FakeStoreOrderRepository extends JpaRepository<FakeStoreOrder, Long> {
    @Override
    FakeStoreOrder save(FakeStoreOrder fakeStoreOrder);

    Optional<FakeStoreOrder> findById(Long orderId);

    List<FakeStoreOrder> findByUserId(Long userId);
}
