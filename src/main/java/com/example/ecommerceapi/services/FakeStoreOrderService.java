package com.example.ecommerceapi.services;

import com.example.ecommerceapi.exceptions.InvalidOrderIdException;
import com.example.ecommerceapi.models.FakeStoreOrder;
import com.example.ecommerceapi.repositories.FakeStoreOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreOrderService {
    FakeStoreOrderRepository fakeStoreOrderRepository;

    public FakeStoreOrderService(FakeStoreOrderRepository fakeStoreOrderRepository) {
        this.fakeStoreOrderRepository = fakeStoreOrderRepository;
    }

//    TODO
    public FakeStoreOrder placeFakeStoreOrder(FakeStoreOrder fakeStoreOrder) {
        return fakeStoreOrderRepository.save(fakeStoreOrder);
    }

    public FakeStoreOrder getFakeStoreOrder(Long fakeStoreOrderId) throws InvalidOrderIdException {
        Optional<FakeStoreOrder> fakeStoreOrderOptional = fakeStoreOrderRepository.findById(fakeStoreOrderId);
        if (fakeStoreOrderOptional.isEmpty()) {
            throw new InvalidOrderIdException("Invalid Order ID");
        }
        return fakeStoreOrderOptional.get();
    }

    public List<FakeStoreOrder> getAllFakeStoreOrdersByUserId(Long userId) {
        return fakeStoreOrderRepository.findByUserId(userId);
    }
}