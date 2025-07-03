package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // View order by ID
    @GetMapping("/view")
    public String viewOrder(@RequestParam Long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            Order o = order.get();
            return "Order ID: " + orderId 
                + " | Product: " + o.getProductName() 
                + " | Quantity: " + o.getQuantity() 
                + " | Price: $" + o.getPrice();
        } else {
            return "Order ID not found.";
        }
    }

    // Add new order
    @PostMapping("/add")
    public String addOrder(@RequestParam String productName,
                           @RequestParam int quantity,
                           @RequestParam double price) {
        Order order = new Order();
        order.setProductName(productName);
        order.setQuantity(quantity);
        order.setPrice(price);

        orderService.createOrder(order);
        return "Order Added: " + productName;
    }

    // Update existing order
    @PostMapping("/update")
    public String updateOrder(@RequestParam Long orderId,
                              @RequestParam String productName,
                              @RequestParam int quantity,
                              @RequestParam double price) {
        Optional<Order> existingOrder = orderService.getOrderById(orderId);
        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setProductName(productName);
            order.setQuantity(quantity);
            order.setPrice(price);
            orderService.createOrder(order);
            return "Order Updated: " + orderId;
        } else {
            return "Order ID not found!";
        }
    }

    // List all orders
    @GetMapping("/all")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Delete order
    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "Order with ID " + id + " deleted.";
    }
}
