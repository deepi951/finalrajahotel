package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CateringBookingActivity extends AppCompatActivity {

    EditText nameEt, emailEt, eventNameEt, phoneEt, altPhoneEt, guestCountEt, locationEt, requirementsEt;
    RadioGroup menuTypeRg;
    TextView sampleItemsTv, contactInfoTv;
    Button submitBtn;

    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_booking);

        // Initialize views
        nameEt = findViewById(R.id.userNameEt);
        emailEt = findViewById(R.id.userEmailEt);
        eventNameEt = findViewById(R.id.eventNameEt);
        phoneEt = findViewById(R.id.phoneEt);
        altPhoneEt = findViewById(R.id.altPhoneEt);
        guestCountEt = findViewById(R.id.guestCountEt);
        locationEt = findViewById(R.id.locationEt);
        requirementsEt = findViewById(R.id.requirementsEt);
        menuTypeRg = findViewById(R.id.menuTypeRg);
        sampleItemsTv = findViewById(R.id.sampleItemsTv);
        contactInfoTv = findViewById(R.id.contactInfoTv);
        submitBtn = findViewById(R.id.submitBtn);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("CateringBookings");

        // Show contact info
        contactInfoTv.setText("For enquiries:\nWhatsApp: +91 9876543210\nPhone: +91 9876543210\nEmail: srirajahotel@gmail.com");

        // Menu type selection
        menuTypeRg.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            String menuType = rb.getText().toString();
            displaySampleItems(menuType);
        });

        // Submit enquiry
        submitBtn.setOnClickListener(v -> submitEnquiry());
    }

    private void displaySampleItems(String menuType) {
        StringBuilder items = new StringBuilder("Sample " + menuType + " Items:\n");
        if (menuType.equals("Veg")) {
            items.append("- Paneer Butter Masala: Rs. 250\n");
            items.append("- Veg Biryani: Rs. 200\n");
            items.append("- Mixed Veg Curry: Rs. 180\n");
            items.append("- Raita: Rs. 50\n");
        } else {
            items.append("- Chicken Curry: Rs. 300\n");
            items.append("- Mutton Biryani: Rs. 350\n");
            items.append("- Fish Fry: Rs. 250\n");
            items.append("- Egg Curry: Rs. 200\n");
        }
        sampleItemsTv.setText(items.toString());
    }

    private void submitEnquiry() {
        // Debug: Confirm button is working
        Toast.makeText(this, "Submit button clicked!", Toast.LENGTH_SHORT).show();

        // Check if user is logged in
        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Please login first to submit enquiry", Toast.LENGTH_LONG).show();
            return;
        }

        String name = nameEt.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        String eventName = eventNameEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String altPhone = altPhoneEt.getText().toString().trim();
        String guestStr = guestCountEt.getText().toString().trim();
        String location = locationEt.getText().toString().trim();
        String requirements = requirementsEt.getText().toString().trim();

        // Validation
        if (name.isEmpty() || email.isEmpty() || eventName.isEmpty() || phone.isEmpty() || guestStr.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields: Name, Email, Event Name, Phone, Guest Count", Toast.LENGTH_LONG).show();
            return;
        }

        int guestCount = Integer.parseInt(guestStr);
        RadioButton selectedRb = findViewById(menuTypeRg.getCheckedRadioButtonId());
        if (selectedRb == null) {
            Toast.makeText(this, "Please select menu type (Veg or Non-Veg)", Toast.LENGTH_SHORT).show();
            return;
        }
        String menuType = selectedRb.getText().toString();

        // Debug: Show what we're saving
        Toast.makeText(this, "Saving enquiry for: " + name, Toast.LENGTH_SHORT).show();

        // Create booking
        String bookingId = dbRef.push().getKey();
        String userId = mAuth.getCurrentUser().getUid();

        CateringBooking booking = new CateringBooking(bookingId, userId, name, email, phone, altPhone, 
                eventName, "", guestCount, menuType, "", location, requirements, 0.0, "Enquiry");

        // Save to Firebase
        dbRef.child(bookingId).setValue(booking)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "✅ Enquiry submitted successfully!", Toast.LENGTH_LONG).show();
                    
                    // Show confirmation page with details
                    Intent intent = new Intent(CateringBookingActivity.this, CateringConfirmationActivity.class);
                    intent.putExtra("bookingId", bookingId);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("phone", phone);
                    intent.putExtra("altPhone", altPhone);
                    intent.putExtra("eventName", eventName);
                    intent.putExtra("guestCount", guestStr);
                    intent.putExtra("menuType", menuType);
                    intent.putExtra("location", location);
                    intent.putExtra("requirements", requirements);
                    intent.putExtra("status", "Enquiry");
                    
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "❌ Failed to submit: " + e.getMessage() + "\nCheck internet connection and Firebase setup", Toast.LENGTH_LONG).show();
                });
    }
}
