package com.example.rajahotel;

import java.io.Serializable;

public class MenuItem implements Serializable {
    public String itemId;
    public String name;
    public int price;
    public String category; // "Veg" or "Non-Veg"
    public String description;
    public boolean available;

    public MenuItem() {}

    public MenuItem(String itemId, String name, int price, String category, 
                    String description, boolean available) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.available = available;
    }
}

