package com.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    private Long productId;

    private String productName;
    private int stock;

    // Default constructor (required by JPA)
    public Inventory() {
    }

    // Parameterized constructor
    public Inventory(Long productId, String productName, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.stock = stock;
    }

    // Getters and Setters

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Optional: toString for debugging
    @Override
    public String toString() {
        return "Inventory{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", stock=" + stock +
                '}';
    }
}

