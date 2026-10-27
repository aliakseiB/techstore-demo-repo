package com.example.techstore.service;

import com.example.techstore.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Manages product inventory operations.
 */
public class InventoryService {

    private static final int LOW_STOCK_THRESHOLD = 10;
    private static final double DISCOUNT_RATE = 0.15; // Named constant for clarity
    private static final int LARGE_QUANTITY_THRESHOLD = 100; // Named constant
    private static final int MEDIUM_QUANTITY_THRESHOLD = 10; // Named constant

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

    /**
     * Applies a discount to a product based on quantity thresholds.
     *
     * @param productId the identifier of the product
     * @param quantity the quantity of the product being considered
     * @return the product with the applied discount, or an empty Optional if not found
     */
    public Optional<Product> applyDiscount(String productId, int quantity) {
        Optional<Product> optionalProduct = findProduct(productId);

        if (optionalProduct.isEmpty()) {
            return Optional.empty();
        }

        Product product = optionalProduct.get();
        double discountMultiplier;

        if (quantity > LARGE_QUANTITY_THRESHOLD) {
            discountMultiplier = 1 - (DISCOUNT_RATE * 2);
        } else if (quantity > MEDIUM_QUANTITY_THRESHOLD) {
            discountMultiplier = 1 - DISCOUNT_RATE;
        } else {
            discountMultiplier = 1;
        }

        double newPrice = product.price() * discountMultiplier;
        return Optional.of(new Product(product.id(), product.name(), newPrice, product.quantity()));
    }
}