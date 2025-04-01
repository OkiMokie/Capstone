package com.example.elevateretailapp;

import java.util.ArrayList;
import java.util.List;

// cart frag
public class Cart {
    // attributes
    List<String> items = new ArrayList<>();
    int size = items.size();

    public Cart(List<String> items){
        this.items = items;
    }

}
