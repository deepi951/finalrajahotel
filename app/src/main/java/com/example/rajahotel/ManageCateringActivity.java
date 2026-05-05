package com.example.rajahotel;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManageCateringActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView backBtn;
    ArrayList<CateringBooking> bookings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_catering);

        recyclerView = findViewById(R.id.cateringRecyclerView);
        backBtn = findViewById(R.id.backBtnManageCatering);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load catering bookings from Firebase
        loadCateringBookings();

        // Back button
        backBtn.setOnClickListener(v -> finish());
    }

    private void loadCateringBookings() {
        // Load from Firebase (dummy data for now)
        bookings.add(new CateringBooking("BOOK001", "USR001", "John Doe", "john@example.com",
                "9876543210", "", "Birthday Party", "2024-04-15", 50, "Veg", "",
                "123 Main St", "Vegetarian preferred", 5000.0, "Pending"));
        bookings.add(new CateringBooking("BOOK002", "USR002", "Jane Smith", "jane@example.com",
                "9876543211", "", "Wedding", "2024-05-10", 200, "Non Veg", "",
                "456 Oak Ave", "Mixed menu", 25000.0, "Confirmed"));

        CateringAdapter adapter = new CateringAdapter(bookings, this);
        recyclerView.setAdapter(adapter);
    }
}
