package com.example.elevateretailapp;

//Class to be used as an object for items in payment methods fragment
class PaymentMethodItem {

    String cardNum;
    int cardTypeImage;

    public PaymentMethodItem(String cardNum, int cardTypeImage) {
        this.cardNum = cardNum;
        this.cardTypeImage = cardTypeImage;
    }

    public String getCardNum() {
        return "**** **** **** " + cardNum;
    }

    public int getCardTypeImage() {
        return cardTypeImage;
    }
}
