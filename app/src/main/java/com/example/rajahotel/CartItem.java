package com.example.rajahotel;

public class CartItem {
    public String name;
    public int price;
    public int discount;
    public int quantity;
    public String imageUrl; // Added to sync with Admin Panel

    public CartItem() {} // Required for Firebase

    public CartItem(String name, int price, int discount, int quantity, String imageUrl) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public int getFinalPrice() {
        if (discount > 0) {
            return price - (price * discount / 100);
        }
        return price;
    }
}
