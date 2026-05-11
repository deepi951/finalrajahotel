package com.example.rajahotel;

import java.io.Serializable;

public class MenuItem implements Serializable {

    public String itemId;

    public String name;

    public int price;

    public String category;

    public String description;

    public String imageUrl;

    public int discount;

    public boolean isAvailable;

    // EMPTY CONSTRUCTOR FOR FIREBASE
    public MenuItem() {
    }

    // FULL CONSTRUCTOR
    public MenuItem(
            String itemId,
            String name,
            int price,
            String category,
            String description,
            String imageUrl,
            int discount,
            boolean isAvailable
    ) {

        this.itemId = itemId;

        this.name = name;

        this.price = price;

        this.category = category;

        this.description = description;

        this.imageUrl = imageUrl;

        this.discount = discount;

        this.isAvailable = isAvailable;
    }

    // Helper to get the price after discount
    public int getFinalPrice() {
        if (discount > 0) {
            return price - (price * discount / 100);
        }
        return price;
    }
}
