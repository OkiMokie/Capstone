package com.example.elevateretailapp;

//The object created for the items/products in the profile page that show name, image, and price
class ProfileProductItem {
    private int productImage;
    private String productName;
    private String productPrice;
    private int id;

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
