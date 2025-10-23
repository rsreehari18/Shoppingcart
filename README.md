# Shopping Cart Application

A simple shopping cart application built with Java Swing.

## Features

- Browse available products
- View product details
- Add items to cart
- Remove items from cart
- Clear entire cart
- View cart total
- Checkout with order summary

## Requirements

- Java Development Kit (JDK) 8 or higher

## How to Compile

```bash
javac -d bin src/com/shopping/*.java
```

## How to Run

```bash
java -cp bin com.shopping.ShoppingCartGUI
```

## Usage

1. Select a product from the "Available Products" list to view its details
2. Click "Add to Cart" to add the selected product to your shopping cart
3. View your cart items in the "Your Cart" section
4. Remove individual items by selecting them and clicking "Remove Selected"
5. Clear all items with the "Clear Cart" button
6. View the total price at the bottom
7. Click "Checkout" to complete your purchase

## Project Structure

```
Shoppingcart/
├── src/
│   └── com/
│       └── shopping/
│           ├── Product.java           # Product model class
│           ├── ShoppingCart.java      # Cart management class
│           └── ShoppingCartGUI.java   # Main GUI application
├── .gitignore
└── README.md
```