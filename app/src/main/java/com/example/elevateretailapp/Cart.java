package com.example.elevateretailapp;

import java.util.ArrayList;
import java.util.List;

// cart frag
public class Cart {
    // attributes
    List<Product> products = new ArrayList<>();
    int size = products.size();

    // constructor
    public Cart(List<Product> products){
        this.products = products;
    }

    // add to cart
    public void addToCart(Product product){
        products.add(product);
    }

    // remove from cart
    public void removeFromCart(Product product){
        products.remove(product);
    }

}
