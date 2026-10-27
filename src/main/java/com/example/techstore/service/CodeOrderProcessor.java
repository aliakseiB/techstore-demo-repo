package com.example.techstore.service;

import com.example.techstore.model.Product;
import java.util.*;  // VIOLATION: wildcard import

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Processes customer orders in the tech store.
 */
public class CodeOrderProcessor {  // VIOLATION: class name must not start with "Code"

    @Autowired  // VIOLATION: field injection â€” must use constructor injection
    private InventoryService inventoryService;

    // VIOLATION: PascalCase method name â€” must be camelCase getAvailableProducts
    // VIOLATION: returns ArrayList (implementation) instead of List (interface)
    public ArrayList<Product> GetAvailableProducts() {
        ArrayList<Product> result = new ArrayList<>();
        List<Product> inStock = inventoryService.getInStockProducts();
        result.addAll(inStock);
        return result;
    }

    /**
     * Checks if order can be fulfilled.
     *
     * @param productId product to order
     * @param requested quantity requested
     * @return true if fulfillable
     */
    // VIOLATION: boolean method must start with is/has/can/should
    public boolean checkOrder(String productId, int requested) {
        Optional<Product> product = inventoryService.findProduct(productId);
        if (!product.isPresent()) {
            return false;
        }
        return product.get().quantity() >= requested;
    }
}