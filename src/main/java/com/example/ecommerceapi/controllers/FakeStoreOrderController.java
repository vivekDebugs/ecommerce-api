package com.example.ecommerceapi.controllers;

import com.example.ecommerceapi.dtos.FakeStoreOrderRequestDTO;
import com.example.ecommerceapi.dtos.FakeStoreOrderResponseDTO;
import com.example.ecommerceapi.exceptions.InvalidOrderIdException;
import com.example.ecommerceapi.models.FakeStoreOrder;
import com.example.ecommerceapi.services.FakeStoreOrderService;
import com.example.ecommerceapi.services.StripePaymentService;
import com.stripe.exception.StripeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fakestore/orders")
public class FakeStoreOrderController {
    FakeStoreOrderService fakeStoreOrderService;
    StripePaymentService stripePaymentService;

    public FakeStoreOrderController(FakeStoreOrderService fakeStoreOrderService) {
        this.fakeStoreOrderService = fakeStoreOrderService;
        this.stripePaymentService = new StripePaymentService();
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

    @PostMapping("/pay")
    public ResponseEntity getPaymentLink() {
        try {
            return new ResponseEntity(this.stripePaymentService.createStripePaymentLink(), HttpStatus.OK);
        } catch (StripeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    TODO: send email
    @GetMapping("/after_completion/{id}")
    public ResponseEntity paymentCompletionHandler(@PathVariable("id") String checkoutSessionId) {
        try {
            if (this.stripePaymentService.isPaymentSuccessful(checkoutSessionId)) {
                return new ResponseEntity<>("Payment Successful", HttpStatus.OK);
            }
            return new ResponseEntity<>("Payment Failed", HttpStatus.BAD_REQUEST);
        } catch (StripeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
