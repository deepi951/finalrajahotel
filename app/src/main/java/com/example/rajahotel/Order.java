package com.example.rajahotel;

import com.google.firebase.firestore.ServerTimestamp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
    public String orderId;
    public String userId;
    public String customerName;
    public String customerEmail;
    public String customerPhone;
    public int totalAmount;
    public String status;
    public String paymentStatus;
    public String paymentMethod;
    public String deliveryAddress;
    public String orderDate;
    
    @ServerTimestamp
    public Date timestamp;
    
    public ArrayList<CartItem> items;

    public Order() {} // Required for Firebase

    public Order(String orderId, String userId, String customerName, String customerEmail, 
                 String customerPhone, int totalAmount, String status, String paymentStatus,
                 String paymentMethod, String deliveryAddress, String orderDate, 
                 ArrayList<CartItem> items) {
        this.orderId = orderId;
        this.userId = userId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = orderDate;
        this.items = items;
    }
}
