package com.example.elevateretailapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//The item used to represent one item wishlisted in the wishlist fragments recyclerview
class WishlistItem {

    String itemName;
    String itemDate;
    int itemImage;

    public WishlistItem(String itemName, String itemDate, int itemImage) {
        this.itemName = itemName;
        this.itemDate = itemDate;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDate() {
        return itemDate;
    }

    public int getItemImage() {
        return itemImage;
    }


    }

