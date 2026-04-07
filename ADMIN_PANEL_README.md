# Sri Raja Hotel - Admin Panel Documentation

## Overview
The Admin Panel is a comprehensive management system integrated into the Sri Raja Hotel app that allows administrators to manage menu items, orders, catering bookings, and customer information.

---

## 📋 Features

### 1. **Admin Login** 🔐
- Secure authentication using Firebase
- Email verification requirement
- Accessible from the main login screen via "Admin Login 🔐" link

**Activity:** `AdminLoginActivity`
**Layout:** `activity_admin_login.xml`

---

### 2. **Admin Dashboard** 📊
- Central hub for all admin operations
- Quick navigation to all management modules
- Logout functionality

**Features:**
- 📋 Manage Menu Items
- 📦 View Orders
- 🎉 Manage Catering Bookings
- 👥 View Customer Details
- 🚪 Logout

**Activity:** `AdminDashboardActivity`
**Layout:** `activity_admin_dashboard.xml`

---

### 3. **Manage Menu Items** 🍽️

#### Add/Edit Menu Items
- Add new menu items with:
  - Item Name
  - Price (in Rs.)
  - Category (Veg 🌱 / Non-Veg 🍗)
  - Description
  - Availability Status (Toggle)

- Edit existing items by selecting from the list
- Delete items (only visible in edit mode)

**Activities:** 
- `ManageMenuActivity` - View and manage items
- `AddEditMenuItemActivity` - Add or edit individual items

**Adapters:** `MenuItemAdapter`
**Layouts:** 
- `activity_manage_menu.xml`
- `activity_add_edit_menu_item.xml`
- `menu_item_admin_row.xml`

---

### 4. **View Orders** 📦

#### Order Management
- View all customer orders in a list
- Key information displayed:
  - Order ID
  - Customer Name
  - Total Price
  - Order Date
  - Current Status (Pending, Preparing, Ready, Delivered)

- Click on any order to view detailed information and update status

**Activities:**
- `ViewOrdersActivity` - List all orders
- `OrderDetailsActivity` - View and manage individual order

**Adapter:** `OrderAdapter`
**Layouts:**
- `activity_view_orders.xml`
- `activity_order_details.xml`
- `order_admin_row.xml`

#### Order Details
When viewing an order, admins can see:
- Order ID and Date
- Customer Details (Name, Email, Phone)
- Delivery Address
- Order Items (with quantities)
- Total Price
- **Update Order Status** using dropdown:
  - Pending
  - Preparing
  - Ready
  - Delivered

---

### 5. **Manage Catering Bookings** 🎉

#### Catering Management
- View all catering booking requests
- Information displayed:
  - Booking ID
  - Customer Name
  - Event Type (Birthday, Wedding, Corporate, etc.)
  - Event Date
  - Guest Count
  - Estimated Cost
  - Booking Status (Pending, Confirmed, Completed, Cancelled)

- Update booking status
- View complete booking details

**Activity:** `ManageCateringActivity`
**Adapter:** `CateringAdapter`
**Layout:** 
- `activity_manage_catering.xml`
- `catering_booking_row.xml`

---

### 6. **View Customer Details** 👥

#### Customer Management
- View all registered customers
- Customer information:
  - Full Name
  - Email Address
  - Phone Number
  - Address
  - Join Date (Account Creation Date)

- View customer order history and preferences
- Manage customer information if needed

**Activity:** `ViewCustomersActivity`
**Adapter:** `CustomerAdapter`
**Layouts:**
- `activity_view_customers.xml`
- `customer_row.xml`

---

## 🏗️ Architecture

### Data Models

#### Order.java
```java
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
    public String items;
}
```

#### MenuItem.java
```java
public class MenuItem implements Serializable {
    public String itemId;
    public String name;
    public int price;
    public String category; // "Veg" or "Non-Veg"
    public String description;
    public boolean available;
}
```

#### CateringBooking.java
```java
public class CateringBooking {
    public String bookingId;
    public String userId;
    public String userName;
    public String userEmail;
    public String userPhone;
    public String eventType;
    public String eventDate;
    public int guestCount;
    public String location;
    public String requirements;
    public double estimatedCost;
    public String status; // "Pending", "Confirmed", "Completed", "Cancelled"
}
```

#### User.java
```java
public class User {
    public String userId;
    public String fullName;
    public String email;
    public String phone;
    public String address;
    public boolean isAdmin;
    public String createdDate;
}
```

---

## 🔗 Navigation Flow

```
LoginActivity
├── Customer Login → MainActivity (Customer Menu)
└── Admin Login 🔐 → AdminLoginActivity
    └── AdminDashboardActivity
        ├── Manage Menu Items → ManageMenuActivity
        │   └── AddEditMenuItemActivity (Add/Edit)
        ├── View Orders → ViewOrdersActivity
        │   └── OrderDetailsActivity (Update Status)
        ├── Manage Catering → ManageCateringActivity
        └── View Customers → ViewCustomersActivity
```

---

## 🗄️ Firebase Integration Points

> **Note:** Replace these with actual Firebase implementations:

1. **Authentication:**
   - Use Firebase Auth for admin login
   - Verify email before granting admin access

2. **Realtime Database/Firestore:**
   - Store Menu Items in `menu_items` collection
   - Store Orders in `orders` collection
   - Store Catering Bookings in `catering_bookings` collection
   - Store User Data in `users` collection

3. **Database Structure Example:**
```
firebase_root/
├── menu_items/
│   ├── item_1/
│   │   ├── name: "Idly"
│   │   ├── price: 10
│   │   ├── category: "Veg"
│   │   └── available: true
│   └── item_2/...
├── orders/
│   ├── order_1/
│   │   ├── userId: "user_1"
│   │   ├── status: "Pending"
│   │   └── totalPrice: 250
│   └── order_2/...
├── catering_bookings/...
└── users/...
```

---

## 📱 Layout Files

### Admin Panel Layouts
- `activity_admin_login.xml` - Admin login screen
- `activity_admin_dashboard.xml` - Main admin dashboard
- `activity_manage_menu.xml` - Menu management list
- `activity_add_edit_menu_item.xml` - Add/edit menu item form
- `menu_item_admin_row.xml` - Menu item list row
- `activity_view_orders.xml` - Orders list
- `activity_order_details.xml` - Order details & status update
- `order_admin_row.xml` - Order list row
- `activity_manage_catering.xml` - Catering bookings list
- `catering_booking_row.xml` - Catering booking row
- `activity_view_customers.xml` - Customers list
- `customer_row.xml` - Customer list row

---

## 🔐 Security Considerations

1. **Admin Authentication:**
   - Always verify email before granting access
   - Consider role-based access control (RBAC) in Firebase
   - Implement admin flag in user profile

2. **Data Protection:**
   - Use Firebase Security Rules to protect sensitive data
   - Only allow authenticated admins to modify menu/orders
   - Log all admin activities for audit

3. **Input Validation:**
   - Validate all input on client and server side
   - Sanitize menu item descriptions
   - Check phone number format

---

## 🚀 Future Enhancements

1. **Analytics Dashboard**
   - Sales reports and charts
   - Most ordered items
   - Revenue tracking
   - Popular time slots

2. **Notifications**
   - Notify customers when order status changes
   - Send admin notifications for new orders
   - SMS/Email alerts for catering bookings

3. **Advanced Features**
   - Bulk import menu items
   - Order scheduling/recurring orders
   - Discount and promo code management
   - Payment integration tracking

4. **Reporting**
   - Daily/Weekly/Monthly reports
   - Customer analytics
   - Inventory management
   - Staff management

---

## 📞 Support

For issues or questions regarding the admin panel, please:
1. Check the data models and adapters
2. Verify Firebase database structure
3. Ensure all activities are registered in AndroidManifest.xml
4. Check logcat for debugging information

---

## ✅ Checklist for Implementation

- [ ] Set up Firebase Realtime Database
- [ ] Create Firebase collections/nodes
- [ ] Update AdminLoginActivity with Firebase verification
- [ ] Implement Firebase data loading in ViewOrdersActivity
- [ ] Implement Firebase data loading in ManageMenuActivity
- [ ] Implement Firebase data loading in ManageCateringActivity
- [ ] Implement Firebase data loading in ViewCustomersActivity
- [ ] Add Firebase write operations for menu items
- [ ] Add Firebase update operations for order status
- [ ] Test all admin functions
- [ ] Add proper error handling and loading states
- [ ] Implement user role verification (isAdmin flag)

---

**Created:** March 30, 2026
**Version:** 1.0
**Sri Raja Hotel Admin Panel**

