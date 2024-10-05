package com.example.ecommerceapi.controllers;

import com.example.ecommerceapi.dtos.FakeStoreOrderRequestDTO;
import com.example.ecommerceapi.dtos.FakeStoreOrderResponseDTO;
import com.example.ecommerceapi.exceptions.InvalidOrderIdException;
import com.example.ecommerceapi.models.FakeStoreOrder;
import com.example.ecommerceapi.services.FakeStoreOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fakestore/orders")
public class FakeStoreOrderController {
    FakeStoreOrderService fakeStoreOrderService;

    public FakeStoreOrderController(FakeStoreOrderService fakeStoreOrderService) {
        this.fakeStoreOrderService = fakeStoreOrderService;
    }

    @PostMapping("")
    public FakeStoreOrderResponseDTO placeFakeStoreOrder(@RequestBody FakeStoreOrderRequestDTO fakeStoreOrderRequestDTO) {
        FakeStoreOrder fakeStoreOrder = this.fakeStoreOrderService.placeFakeStoreOrder(fakeStoreOrderRequestDTO.toFakeStoreOrder());
        return FakeStoreOrderResponseDTO.fromFakeStoreOrder(fakeStoreOrder);
    }

//    TODO: get userId from token
    @GetMapping("")
    public List<FakeStoreOrderResponseDTO> getAllFakeStoreOrdersByUserId(@RequestParam("userId") Long userId) {
        List<FakeStoreOrder> listOfFakeStoreOrders = this.fakeStoreOrderService.getAllFakeStoreOrdersByUserId(userId);
        List<FakeStoreOrderResponseDTO> listOfFakeStoreOrderResponseDTOS = new ArrayList<>();
        for (FakeStoreOrder fakeStoreOrder : listOfFakeStoreOrders) {
            listOfFakeStoreOrderResponseDTOS.add(FakeStoreOrderResponseDTO.fromFakeStoreOrder(fakeStoreOrder));
        }
        return listOfFakeStoreOrderResponseDTOS;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FakeStoreOrderResponseDTO> getFakeStoreOrder(@PathVariable("id") Long fakeStoreOrderId) {
        try {
            FakeStoreOrder fakeStoreOrder = this.fakeStoreOrderService.getFakeStoreOrder(fakeStoreOrderId);
            return new ResponseEntity<>(FakeStoreOrderResponseDTO.fromFakeStoreOrder(fakeStoreOrder), HttpStatus.OK);
        } catch (InvalidOrderIdException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
