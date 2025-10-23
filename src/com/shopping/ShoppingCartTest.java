package com.shopping;

/**
 * Simple test class to verify shopping cart functionality
 */
public class ShoppingCartTest {
    public static void main(String[] args) {
        System.out.println("=== Shopping Cart Test ===\n");

        // Create products
        Product laptop = new Product("Laptop", 999.99, "High-performance laptop");
        Product mouse = new Product("Mouse", 29.99, "Wireless mouse");
        Product keyboard = new Product("Keyboard", 79.99, "Mechanical keyboard");

        // Create shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Test adding items
        System.out.println("Adding items to cart...");
        cart.addItem(laptop);
        cart.addItem(mouse);
        cart.addItem(keyboard);
        System.out.println("Items added: " + cart.getItemCount());

        // Test viewing cart
        System.out.println("\nCart contents:");
        for (Product item : cart.getItems()) {
            System.out.println("  - " + item);
        }

        // Test total
        System.out.println("\nCart total: $" + String.format("%.2f", cart.getTotal()));

        // Test removing item
        System.out.println("\nRemoving item at index 1 (Mouse)...");
        cart.removeItem(1);
        System.out.println("Items remaining: " + cart.getItemCount());

        // Test total after removal
        System.out.println("\nNew cart total: $" + String.format("%.2f", cart.getTotal()));

        // Test clearing cart
        System.out.println("\nClearing cart...");
        cart.clear();
        System.out.println("Items in cart: " + cart.getItemCount());
        System.out.println("Cart total: $" + String.format("%.2f", cart.getTotal()));

        System.out.println("\n=== Test completed successfully! ===");
    }
}
