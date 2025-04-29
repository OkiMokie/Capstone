package com.example.elevateretailapp;

import java.util.List;

//Manager that controls access to the cart/makes sure there is access to one cart in app at all times
public class CartManager {
    private static final Cart cart = new Cart();

    // Add to cart
    public static void addToCart(Product product) {
        cart.addToCart(product);
    }

    // Remove from cart
    public static void removeFromCart(Product product) {
        cart.removeFromCart(product);
    }

    // Get all items
    public static List<Product> getCartItems() {
        return cart.getCartItems();
    }

    // Clear the cart (optional)
    public static void clearCart() {
        cart.getCartItems().clear();  // or make cart.clear() if you prefer
    }

    // Get cart size
    public static int getCartSize() {
        return cart.getSize();
    }
}
