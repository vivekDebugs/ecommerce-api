package com.example.ecommerceapi.dtos;

import com.example.ecommerceapi.models.FakeStoreOrder;
import com.example.ecommerceapi.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreOrderRequestDTO {
    private UserDTO user;
    private Long fakeStoreProductId;
    private Integer quantity;

    public FakeStoreOrder toFakeStoreOrder() {
        FakeStoreOrder fakeStoreOrder = new FakeStoreOrder();
        fakeStoreOrder.setUser(user.toUser());
        fakeStoreOrder.setFakeStoreProductId(fakeStoreProductId);
        fakeStoreOrder.setQuantity(quantity);
        return fakeStoreOrder;
    }
}
