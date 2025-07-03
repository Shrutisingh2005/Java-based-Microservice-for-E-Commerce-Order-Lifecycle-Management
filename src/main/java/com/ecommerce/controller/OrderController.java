package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Handle form to view order by ID
    @GetMapping("/view")
    public String viewOrder(@RequestParam Long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            return "Order ID: " + orderId + " | Details: " + order.get().toString();
        } else {
            return "Order ID not found.";
        }
    }

    // Handle form to add order
    @PostMapping("/add")
    public String addOrder(@RequestParam Long orderId, @RequestParam String orderDetails) {
        // You can create an Order object directly
        Order order = new Order();
        order.setId(orderId);
        order.setDetails(orderDetails);

        orderService.createOrder(order);
        return "Order Added: " + orderId;
    }

    // Handle form to update order
    @PostMapping("/update")
    public String updateOrder(@RequestParam Long orderId, @RequestParam String orderDetails) {
        Optional<Order> existingOrder = orderService.getOrderById(orderId);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setDetails(orderDetails);
            orderService.createOrder(order); // You can use save or update in your service
            return "Order Updated: " + orderId;
        } else {
            return "Order ID not found!";
        }
    }

    // Optional: Expose all orders
    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Optional: Delete Order (if needed)
    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "Order with ID " + id + " has been deleted.";
    }
}
