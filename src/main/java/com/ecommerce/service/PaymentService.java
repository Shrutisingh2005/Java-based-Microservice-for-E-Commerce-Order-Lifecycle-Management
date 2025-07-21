package com.example.razorpayintegration.service;

import com.example.razorpayintegration.dto.*;
import com.example.razorpayintegration.util.SignatureUtil;
import com.razorpay.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    public CreateOrderResponse createOrder(CreateOrderRequest request) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(keyId, keySecret);

        JSONObject options = new JSONObject();
        options.put("amount", request.getAmount() * 100); // Convert to paise
        options.put("currency", request.getCurrency());
        options.put("receipt", request.getReceipt());
        options.put("payment_capture", 1);

        Order order = client.Orders.create(options);

        return new CreateOrderResponse(order.get("id"), order.get("currency"), order.get("amount"));
    }

    public boolean verifySignature(VerifyPaymentRequest request) throws Exception {
        String data = request.getRazorpayOrderId() + "|" + request.getRazorpayPaymentId();
        String expected = SignatureUtil.calculateHMAC(data, keySecret);
        return expected.equals(request.getRazorpaySignature());
    }
}
