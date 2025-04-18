package com.example.elevateretailapp;

import java.util.ArrayList;
import java.util.List;

// cart frag
public class Cart {
    // attributes
    List<ProfileProductItem> ProfileProductItems = new ArrayList<>();
    int size = ProfileProductItems.size();

    // constructor
    public Cart(List<Product> products){
        this.ProfileProductItems = ProfileProductItems;
    }

    // add to cart
    public void addToCart(ProfileProductItem profileProductItem){
        ProfileProductItems.add(profileProductItem);
    }

    // remove from cart
    public void removeFromCart(ProfileProductItem profileProductItem){
        ProfileProductItems.remove(profileProductItem);
    }
}
