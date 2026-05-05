package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CateringConfirmationActivity extends AppCompatActivity {

    TextView bookingIdTv, nameTv, emailTv, phoneTv, altPhoneTv, eventNameTv, guestCountTv, 
              menuTypeTv, locationTv, requirementsTv, statusTv;
    Button backBtn, homeBtn, codBtn, onlinePaymentBtn;
    String bookingId;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_confirmation);

        // Initialize views
        bookingIdTv = findViewById(R.id.confirmBookingId);
        nameTv = findViewById(R.id.confirmName);
        emailTv = findViewById(R.id.confirmEmail);
        phoneTv = findViewById(R.id.confirmPhone);
        altPhoneTv = findViewById(R.id.confirmAltPhone);
        eventNameTv = findViewById(R.id.confirmEventName);
        guestCountTv = findViewById(R.id.confirmGuestCount);
        menuTypeTv = findViewById(R.id.confirmMenuType);
        locationTv = findViewById(R.id.confirmLocation);
        requirementsTv = findViewById(R.id.confirmRequirements);
        statusTv = findViewById(R.id.confirmStatus);
        backBtn = findViewById(R.id.backBtn);
        homeBtn = findViewById(R.id.homeBtn);
        codBtn = findViewById(R.id.codBtn);
        onlinePaymentBtn = findViewById(R.id.onlinePaymentBtn);

        dbRef = FirebaseDatabase.getInstance().getReference("CateringBookings");

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            bookingId = intent.getStringExtra("bookingId");
            String name = intent.getStringExtra("name");
            String email = intent.getStringExtra("email");
            String phone = intent.getStringExtra("phone");
            String altPhone = intent.getStringExtra("altPhone");
            String eventName = intent.getStringExtra("eventName");
            String guestCount = intent.getStringExtra("guestCount");
            String menuType = intent.getStringExtra("menuType");
            String location = intent.getStringExtra("location");
            String requirements = intent.getStringExtra("requirements");
            String status = intent.getStringExtra("status");

            // Display data
            bookingIdTv.setText("Booking ID: " + bookingId);
            nameTv.setText("Name: " + name);
            emailTv.setText("Email: " + email);
            phoneTv.setText("Phone: " + phone);
            altPhoneTv.setText("Alternate Phone: " + altPhone);
            eventNameTv.setText("Event Name: " + eventName);
            guestCountTv.setText("Guest Count: " + guestCount);
            menuTypeTv.setText("Menu Type: " + menuType);
            locationTv.setText("Location: " + location);
            requirementsTv.setText("Requirements: " + requirements);
            statusTv.setText("Status: " + status);
        }

        // Back button
        backBtn.setOnClickListener(v -> finish());

        // Home button - with proper navigation
        homeBtn.setOnClickListener(v -> {
            Intent homeIntent = new Intent(CateringConfirmationActivity.this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeIntent);
            finish();
        });

        // COD Payment
        codBtn.setOnClickListener(v -> {
            updatePaymentMethod("COD");
            Toast.makeText(this, "✅ COD Payment Selected\nOur team will contact you for payment details", Toast.LENGTH_LONG).show();
        });

        // Online Payment
        onlinePaymentBtn.setOnClickListener(v -> {
            updatePaymentMethod("Online Payment");
            Toast.makeText(this, "✅ Online Payment Selected\nPayment details will be sent to your email", Toast.LENGTH_LONG).show();
            // TODO: Integrate payment gateway here (Razorpay, PayPal, etc.)
        });
    }

    private void updatePaymentMethod(String paymentMethod) {
        // Update the booking with payment method in Firebase
        dbRef.child(bookingId).child("paymentMethod").setValue(paymentMethod)
                .addOnSuccessListener(aVoid -> {
                    // Payment method updated successfully
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error updating payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}

