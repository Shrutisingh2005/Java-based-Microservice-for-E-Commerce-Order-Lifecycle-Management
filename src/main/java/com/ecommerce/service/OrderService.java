package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryService inventoryService;

    private final Random random = new Random();

    // Create Order with inventory check and payment simulation
    public Order createOrder(Order order) {
        // 1. Check inventory stock
        boolean stockAvailable = inventoryService.isStockAvailable(order.getProductId(), order.getQuantity());
        if (!stockAvailable) {
            order.setStatus("FAILED_INVENTORY");
            return orderRepository.save(order);
        }

        // 2. Simulate payment (80% success rate)
        boolean paymentSuccess = random.nextDouble() < 0.8;
        if (!paymentSuccess) {
            order.setStatus("FAILED_PAYMENT");
            return orderRepository.save(order);
        }

        // 3. If payment succeeds, reduce stock and mark order placed
        inventoryService.reduceStock(order.getProductId(), order.getQuantity());
        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    // Get All Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get Order by ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Delete Order
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
