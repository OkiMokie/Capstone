package com.example.elevateretailapp;

//The object created for the items/products in the profile page that show name, image, and price
class ProfileProductItem {
    int productImage;
    String productName;
    String productPrice;

    public ProfileProductItem(int productImage, String productName, String productPrice) {
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }
}
