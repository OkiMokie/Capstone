package com.example.elevateretailapp;


//Item/class/object and its attributes for the items in the transaction history fragment
class OrderedTransactionItem {
    String orderedItem;
    String orderedItemDate;
    String orderedItemStatus;
    int orderedImage;

    public OrderedTransactionItem(int orderedImage, String orderedItem, String orderedItemDate, String orderedItemStatus) {
        this.orderedItem = orderedItem;
        this.orderedItemDate = orderedItemDate;
        this.orderedImage = orderedImage;
        this.orderedItemStatus = orderedItemStatus;
    }

    public String getOrderedItem() {
        return orderedItem;
    }

    public String getOrderedItemDate() {
        return orderedItemDate;
    }

    public int getOrderedImage() { return orderedImage; }

    public String getOrderedItemStatus() { return orderedItemStatus; }
}
