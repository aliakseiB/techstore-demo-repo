package com.example.techstore.model;

/**
 * Represents a product in the tech store inventory.
 *
 * @param id       unique product identifier
 * @param name     product display name
 * @param price    price in USD
 * @param quantity available stock quantity
 */
public record Product(String id, String name, double price, int quantity) {
}