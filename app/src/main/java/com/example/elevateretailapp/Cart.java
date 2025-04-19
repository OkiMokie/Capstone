package com.example.elevateretailapp;

import java.util.ArrayList;
import java.util.List;

// cart frag
public class Cart {
    // attributes
    List<Product> Products = new ArrayList<>();
    int size = Products.size();

    // constructor
    public Cart(List<Product> products){
        this.Products = Products;
    }

    // add to cart
    public void addToCart(Product Product){
        Products.add(Product);
    }

    // remove from cart
    public void removeFromCart(Product product){
        Products.remove(product);
    }
}
