package com.example.elevateretailapp;

import java.util.ArrayList;
import java.util.List;

// cart frag
public class Cart {
    // attributes
    private List<Product> productList = new ArrayList<>();
    int size = productList.size();

    // constructor
    public Cart(List<Product> products){
        this.productList = productList;
    }

    // empty overloaded
    public Cart() {}

    // add to cart
    public void addToCart(Product product){
        productList.add(product);
    }

    // remove from cart
    public void removeFromCart(Product profileProductItem){
        productList.remove(profileProductItem);
    }

    //returns product cart
    public List<Product> getCartItems() {
        return productList;
    }

    //returns the amount of things in there
    public int getSize() {
        return productList.size();
    }
}
