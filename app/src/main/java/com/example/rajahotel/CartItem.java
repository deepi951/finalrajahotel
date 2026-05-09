package com.example.rajahotel;

public class CartItem {
    public String name;
    public int price;
    public int discount;
    public int quantity;
    public int image; // Drawable resource id

    public CartItem(String name, int price, int discount, int quantity, int image) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.image = image;
    }

    // Constructor for items without discount
    public CartItem(String name, int price, int quantity, int image) {
        this.name = name;
        this.price = price;
        this.discount = 0;
        this.quantity = quantity;
        this.image = image;
    }

    // Helper to get the price after discount
    public int getFinalPrice() {
        if (discount > 0) {
            return price - (price * discount / 100);
        }
        return price;
    }
}
