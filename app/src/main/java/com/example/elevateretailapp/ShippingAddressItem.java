package com.example.elevateretailapp;

//The shipping address object used for the item representing that address information
//Used for the item layout displaying in the shipping address fragments recyclerview
class ShippingAddressItem {

    String name;
    String address1;
    String address2;
    String city;
    String state;
    String zip;
    String country;

    public ShippingAddressItem(String name, String address1, String address2, String city, String state, String zip, String country) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public ShippingAddressItem(String name, String address1, String city, String state, String zip, String country) {
        this.name = name;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }
}
