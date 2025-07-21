package com.ecommerce.service;

import com.ecommerce.model.Inventory;
import com.ecommerce.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository repo;

    public InventoryService(InventoryRepository repo) {
        this.repo = repo;
    }

    public boolean checkStock(Long productId, int qty) {
        Optional<Inventory> item = repo.findById(productId);
        return item.isPresent() && item.get().getStock() >= qty;
    }

    public void decreaseStock(Long productId, int qty) {
        Inventory item = repo.findById(productId).orElseThrow();
        item.setStock(item.getStock() - qty);
        repo.save(item);
    }
}
