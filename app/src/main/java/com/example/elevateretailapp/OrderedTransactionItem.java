package com.example.elevateretailapp;

class OrderedTransactionItem {
    String orderedItem;
    String orderedItemDate;

    public OrderedTransactionItem(String orderedItem, String orderedItemDate) {
        this.orderedItem = orderedItem;
        this.orderedItemDate = orderedItemDate;

    }

    public String getOrderedItem() {
        return orderedItem;
    }

    public String getOrderedItemDate() {
        return orderedItemDate;
    }


}
