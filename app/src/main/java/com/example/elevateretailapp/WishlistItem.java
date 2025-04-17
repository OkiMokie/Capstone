package com.example.elevateretailapp;

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
