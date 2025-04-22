package com.example.elevateretailapp;

public class Product {
    private int product_id;
    private String product_name;
    private String product_description;
    private int category_id;
    private int supplier_id;
    private int imageResId; // drawable resource ID
    private String price; // price as a String like "$9.99"
    private int quantity = 1; // default to 1

    public Product() {}

    public Product(int product_id, String product_name, String product_description, int category_id, int supplier_id, int imageResId, String price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.category_id = category_id;
        this.supplier_id = supplier_id;
        this.imageResId = imageResId;
        this.price = price;
    }

    // Getters and Setters
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_description='" + product_description + '\'' +
                ", category_id=" + category_id +
                ", supplier_id=" + supplier_id +
                ", imageResId=" + imageResId +
                ", price='" + price + '\'' +
                '}';
    }
}
