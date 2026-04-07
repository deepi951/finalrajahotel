package com.example.rajahotel;

public class User {
    public String userId;
    public String fullName;
    public String email;
    public String phone;
    public String address;
    public boolean isAdmin;
    public String createdDate;

    public User() {}

    public User(String userId, String fullName, String email, String phone, 
                String address, boolean isAdmin, String createdDate) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.isAdmin = isAdmin;
        this.createdDate = createdDate;
    }
}

