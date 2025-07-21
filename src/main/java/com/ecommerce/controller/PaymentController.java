package com.example.razorpayintegration.controller;

import com.example.razorpayintegration.dto.*;
import com.example.razorpayintegration.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) throws Exception {
        return ResponseEntity.ok(paymentService.createOrder(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody VerifyPaymentRequest request) throws Exception {
        boolean isValid = paymentService.verifySignature(request);
        return ResponseEntity.ok(isValid ? "Payment verified successfully" : "Invalid payment signature");
    }
}
