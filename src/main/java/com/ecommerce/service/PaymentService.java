package com.ecommerce.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    private final Random random = new Random();

    /**
     * Simulate payment processing.
     * Returns true ~80% of the time to indicate success.
     *
     * @param amount payment amount
     * @return true if payment successful, false if failed
     */
    public boolean processPayment(double amount) {
        // 80% chance of success
        return random.nextDouble() < 0.8;
    }
}
