package com.example.rajahotel;

public class CateringBooking {
    public String bookingId;
    public String userId;
    public String userName;
    public String userEmail;
    public String userPhone;
    public String alternatePhone;
    public String eventName;
    public String eventDate;
    public int guestCount;
    public String menuType;
    public String selectedItems;
    public String location;
    public String requirements;
    public double estimatedCost;
    public String status;
    public String paymentMethod; // NEW

    public CateringBooking() {}

    public CateringBooking(String bookingId, String userId, String userName, String userEmail,
                          String userPhone, String alternatePhone, String eventName, String eventDate, 
                          int guestCount, String menuType, String selectedItems, String location, 
                          String requirements, double estimatedCost, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.alternatePhone = alternatePhone;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.guestCount = guestCount;
        this.menuType = menuType;
        this.selectedItems = selectedItems;
        this.location = location;
        this.requirements = requirements;
        this.estimatedCost = estimatedCost;
        this.status = status;
        this.paymentMethod = ""; // Default empty
    }
}

