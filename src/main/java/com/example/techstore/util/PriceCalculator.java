package com.example.techstore.util;

/**
 * Utility for calculating product prices with discounts.
 */
public final class PriceCalculator {

    private static final double MEMBER_DISCOUNT = 0.10;
    private static final double BULK_DISCOUNT   = 0.15;
    private static final int    BULK_THRESHOLD  = 5;

    private PriceCalculator() {
    }

    /**
     * Calculates the final price after applicable discounts.
     *
     * @param basePrice the original price in USD
     * @param quantity  number of items purchased
     * @param isMember  whether the customer has a membership
     * @return the discounted total price
     */
    public static double calculatePrice(double basePrice, int quantity, boolean isMember) {
        double discount = isMember ? MEMBER_DISCOUNT : 0.0;
        if (quantity >= BULK_THRESHOLD) {
            discount = Math.max(discount, BULK_DISCOUNT);
        }
        return basePrice * quantity * (1.0 - discount);
    }
}