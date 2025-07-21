package com.ecommerce.controller;

import com.ecommerce.model.Inventory;
import com.ecommerce.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryRepository repo;

    public InventoryController(InventoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Inventory> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Inventory addItem(@RequestBody Inventory inv) {
        return repo.save(inv);
    }
}
