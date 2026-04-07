package com.example.rajahotel;

public class CartItem {
    String name;
    int price;
    int quantity;
    int image; // Drawable resource id

    public CartItem(String name, int price, int quantity, int image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }
}