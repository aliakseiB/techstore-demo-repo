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
}