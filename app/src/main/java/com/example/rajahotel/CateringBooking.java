package com.example.rajahotel;

public class CateringBooking {
    public String bookingId;
    public String userId;
    public String userName;
    public String userEmail;
    public String userPhone;
    public String eventType; // Birthday, Wedding, Corporate, etc.
    public String eventDate;
    public int guestCount;
    public String location;
    public String requirements;
    public double estimatedCost;
    public String status; // "Pending", "Confirmed", "Completed", "Cancelled"

    public CateringBooking() {}

    public CateringBooking(String bookingId, String userId, String userName, String userEmail,
                          String userPhone, String eventType, String eventDate, int guestCount,
                          String location, String requirements, double estimatedCost, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.guestCount = guestCount;
        this.location = location;
        this.requirements = requirements;
        this.estimatedCost = estimatedCost;
        this.status = status;
    }
}

