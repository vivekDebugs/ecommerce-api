package com.example.ecommerceapi.dtos;

import com.example.ecommerceapi.models.FakeStoreOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreOrderResponseDTO {
    private UserDTO user;
    private Long fakeStoreProductId;
    private Integer quantity;

    public static FakeStoreOrderResponseDTO fromFakeStoreOrder(FakeStoreOrder fakeStoreOrder) {
        FakeStoreOrderResponseDTO fakeStoreOrderResponseDTO = new FakeStoreOrderResponseDTO();
        fakeStoreOrderResponseDTO.setUser(UserDTO.fromUser(fakeStoreOrder.getUser()));
        fakeStoreOrderResponseDTO.setFakeStoreProductId(fakeStoreOrder.getFakeStoreProductId());
        fakeStoreOrderResponseDTO.setQuantity(fakeStoreOrder.getQuantity());

        return fakeStoreOrderResponseDTO;
    }
}
