package com.shopping;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the shopping cart operations
 */
public class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    public List<Product> getItems() {
        return new ArrayList<>(items);
    }

    public double getTotal() {
        double total = 0.0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }

    public int getItemCount() {
        return items.size();
    }
}
