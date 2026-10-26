package com.example.techstore.service;

import com.example.techstore.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Manages product inventory operations.
 */
public class InventoryService {

    private static final int LOW_STOCK_THRESHOLD = 10;

    private final List<Product> products;

    public InventoryService(List<Product> products) {
        this.products = products;
    }

    /**
     * Finds a product by its identifier.
     *
     * @param productId the product identifier
     * @return the product, or empty if not found
     */
    public Optional<Product> findProduct(String productId) {
        return products.stream()
                .filter(p -> p.id().equals(productId))
                .findFirst();
    }

    /**
     * Returns all products that are currently in stock.
     *
     * @return list of in-stock products
     */
    public List<Product> getInStockProducts() {
        return products.stream()
                .filter(p -> p.quantity() > 0)
                .toList();
    }

    /**
     * Checks whether a product has low stock.
     *
     * @param product the product to check
     * @return true if stock is below the threshold
     */
    public boolean isLowStock(Product product) {
        return product.quantity() < LOW_STOCK_THRESHOLD;
    }

    // VIOLATION: no JavaDoc on public method
    // VIOLATION: non-descriptive variable names (d, p)
    // VIOLATION: returns null instead of Optional
    // VIOLATION: method too long (> 20 lines)
    public Product applyDiscount(String id, int qty) {
        double d = 0.15;   // VIOLATION: magic number, non-descriptive name
        Product p = null;  // VIOLATION: non-descriptive name

        // VIOLATION: manual index loop instead of Stream / enhanced for-loop
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).id().equals(id)) {
                p = products.get(i);
                break;
            }
        }

        if (p == null) {
            return null;  // VIOLATION: should return Optional.empty()
        }

        double newPrice;
        if (qty > 100) {       // VIOLATION: magic number 100 without named constant
            newPrice = p.price() * (1 - d * 2);
        } else if (qty > 10) { // VIOLATION: magic number 10 without named constant
            newPrice = p.price() * (1 - d);
        } else {
            newPrice = p.price();
        }

        try {
            return new Product(p.id(), p.name(), newPrice, p.quantity());
        } catch (Exception e) {
            // VIOLATION: catching generic Exception; exception silently swallowed
        }
        return null;  // VIOLATION: should return Optional.empty()
    }
}