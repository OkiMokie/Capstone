package com.example.elevateretailapp;

public class Product {
    // columns/attributes
    private int product_id;
    private String product_name;
    private String product_description;
    private int category_id;
    private int supplier_id;
    private String image_url;

    // Default constructor (required for some libraries like Retrofit, Gson, Firebase, etc.)
    public Product() {}

    // Full constructor
    public Product(int product_id, String product_name, String product_description, int category_id, int supplier_id, String image_url) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.category_id = category_id;
        this.supplier_id = supplier_id;
        this.image_url = image_url;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_description='" + product_description + '\'' +
                ", category_id=" + category_id +
                ", supplier_id=" + supplier_id +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
