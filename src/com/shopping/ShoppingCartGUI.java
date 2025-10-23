package com.shopping;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main GUI class for the Shopping Cart application using Java Swing
 */
public class ShoppingCartGUI extends JFrame {
    private ShoppingCart cart;
    private DefaultListModel<Product> availableProductsModel;
    private DefaultListModel<String> cartItemsModel;
    private JList<Product> availableProductsList;
    private JList<String> cartItemsList;
    private JLabel totalLabel;
    private JTextArea productDetailsArea;

    public ShoppingCartGUI() {
        cart = new ShoppingCart();
        initializeComponents();
        loadSampleProducts();
    }

    private void initializeComponents() {
        setTitle("Shopping Cart Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Title
        JLabel titleLabel = new JLabel("Shopping Cart", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Center panel with products and cart
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));

        // Left panel - Available Products
        JPanel productsPanel = createProductsPanel();
        centerPanel.add(productsPanel);

        // Right panel - Shopping Cart
        JPanel cartPanel = createCartPanel();
        centerPanel.add(cartPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Bottom panel - Total and Checkout
        JPanel bottomPanel = createBottomPanel();
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createProductsPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Available Products"));

        // Products list
        availableProductsModel = new DefaultListModel<>();
        availableProductsList = new JList<>(availableProductsModel);
        availableProductsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        availableProductsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showProductDetails();
            }
        });

        JScrollPane scrollPane = new JScrollPane(availableProductsList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Product details
        productDetailsArea = new JTextArea(3, 20);
        productDetailsArea.setEditable(false);
        productDetailsArea.setLineWrap(true);
        productDetailsArea.setWrapStyleWord(true);
        productDetailsArea.setBorder(BorderFactory.createTitledBorder("Product Details"));
        panel.add(productDetailsArea, BorderLayout.NORTH);

        // Add to cart button
        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });
        panel.add(addButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Your Cart"));

        // Cart items list
        cartItemsModel = new DefaultListModel<>();
        cartItemsList = new JList<>(cartItemsModel);
        cartItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(cartItemsList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        JButton removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromCart();
            }
        });

        JButton clearButton = new JButton("Clear Cart");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCart();
            }
        });

        buttonsPanel.add(removeButton);
        buttonsPanel.add(clearButton);

        panel.add(buttonsPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 0, 0, 0));

        // Total label
        totalLabel = new JLabel("Total: $0.00", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(totalLabel, BorderLayout.CENTER);

        // Checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setPreferredSize(new Dimension(120, 40));
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkout();
            }
        });
        panel.add(checkoutButton, BorderLayout.EAST);

        return panel;
    }

    private void loadSampleProducts() {
        availableProductsModel.addElement(new Product("Laptop", 999.99, "High-performance laptop with 16GB RAM"));
        availableProductsModel.addElement(new Product("Mouse", 29.99, "Wireless optical mouse"));
        availableProductsModel.addElement(new Product("Keyboard", 79.99, "Mechanical keyboard with RGB lighting"));
        availableProductsModel.addElement(new Product("Monitor", 299.99, "27-inch Full HD monitor"));
        availableProductsModel.addElement(new Product("Headphones", 149.99, "Noise-cancelling headphones"));
        availableProductsModel.addElement(new Product("Webcam", 89.99, "1080p HD webcam"));
        availableProductsModel.addElement(new Product("USB Cable", 9.99, "USB-C to USB-A cable"));
        availableProductsModel.addElement(new Product("External SSD", 199.99, "1TB portable SSD"));
    }

    private void showProductDetails() {
        Product selected = availableProductsList.getSelectedValue();
        if (selected != null) {
            productDetailsArea.setText(
                "Name: " + selected.getName() + "\n" +
                "Price: $" + String.format("%.2f", selected.getPrice()) + "\n" +
                "Description: " + selected.getDescription()
            );
        } else {
            productDetailsArea.setText("");
        }
    }

    private void addToCart() {
        Product selected = availableProductsList.getSelectedValue();
        if (selected != null) {
            cart.addItem(selected);
            updateCartDisplay();
            JOptionPane.showMessageDialog(this,
                "Added " + selected.getName() + " to cart!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "Please select a product to add to cart.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removeFromCart() {
        int selectedIndex = cartItemsList.getSelectedIndex();
        if (selectedIndex >= 0) {
            cart.removeItem(selectedIndex);
            updateCartDisplay();
            JOptionPane.showMessageDialog(this,
                "Item removed from cart!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "Please select an item to remove from cart.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearCart() {
        if (cart.getItemCount() > 0) {
            int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to clear the cart?",
                "Confirm",
                JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                cart.clear();
                updateCartDisplay();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Cart is already empty!",
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateCartDisplay() {
        cartItemsModel.clear();
        for (Product item : cart.getItems()) {
            cartItemsModel.addElement(item.toString());
        }
        totalLabel.setText("Total: $" + String.format("%.2f", cart.getTotal()));
    }

    private void checkout() {
        if (cart.getItemCount() > 0) {
            StringBuilder message = new StringBuilder("Order Summary:\n\n");
            for (Product item : cart.getItems()) {
                message.append(item.toString()).append("\n");
            }
            message.append("\nTotal: $").append(String.format("%.2f", cart.getTotal()));
            message.append("\n\nThank you for your purchase!");

            JOptionPane.showMessageDialog(this,
                message.toString(),
                "Checkout",
                JOptionPane.INFORMATION_MESSAGE);

            cart.clear();
            updateCartDisplay();
        } else {
            JOptionPane.showMessageDialog(this,
                "Your cart is empty!",
                "Empty Cart",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Use SwingUtilities to ensure GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShoppingCartGUI gui = new ShoppingCartGUI();
                gui.setVisible(true);
            }
        });
    }
}
