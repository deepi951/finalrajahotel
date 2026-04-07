package com.example.rajahotel;

import java.io.Serializable;

public class Order implements Serializable {
    public String orderId;
    public String userId;
    public String userName;
    public String userEmail;
    public String userPhone;
    public double totalPrice;
    public String status; // "Pending", "Preparing", "Ready", "Delivered"
    public String orderDate;
    public String deliveryAddress;
    public String items; // JSON string of items

    public Order() {}

    public Order(String orderId, String userId, String userName, String userEmail, 
                 String userPhone, double totalPrice, String status, String orderDate, 
                 String deliveryAddress, String items) {
        this.orderId = orderId;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.items = items;
    }
}

