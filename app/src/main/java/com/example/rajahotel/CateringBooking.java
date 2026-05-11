package com.example.rajahotel;

import java.io.Serializable;

public class CateringBooking implements Serializable {
    public String bookingId;
    public String userId;
    public String name;
    public String mail;           // Matches "mail" in your screenshot
    public String phoneNumber;    // Matches "phoneNumber"
    public String altPhoneNumber; // Matches "altPhoneNumber"
    public String eventName;      // Matches "eventName"
    public String date;
    public int count;             // Matches "count" (Guest count)
    public String menuType;
    public String location;
    public String requirements;
    public double cost;
    public String status;

    public CateringBooking() {}

    public CateringBooking(String bookingId, String userId, String name, String mail,
                          String phoneNumber, String altPhoneNumber, String eventName, String date, 
                          int count, String menuType, String location, 
                          String requirements, double cost, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.name = name;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.altPhoneNumber = altPhoneNumber;
        this.eventName = eventName;
        this.date = date;
        this.count = count;
        this.menuType = menuType;
        this.location = location;
        this.requirements = requirements;
        this.cost = cost;
        this.status = status;
    }
}
