package com.example.techstore.service;

import com.example.techstore.model.Product;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Processes customer orders in the tech store.
 */
public class OrderProcessor {

    private final InventoryService inventoryService;

    public OrderProcessor(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public List<Product> getAvailableProducts() {
        List<Product> result = new ArrayList<>();
        List<Product> inStock = inventoryService.getInStockProducts();
        result.addAll(inStock);
        return result;
    }

    public boolean canFulfillOrder(String productId, int requested) {
        Optional<Product> product = inventoryService.findProduct(productId);
        if (!product.isPresent()) {
            return false;
        }
        return product.get().quantity() >= requested;
    }
}