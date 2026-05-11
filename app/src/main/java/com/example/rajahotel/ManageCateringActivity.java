package com.example.rajahotel;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageCateringActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView backBtn;
    TextView noBookingsTv;
    ArrayList<CateringBooking> bookings = new ArrayList<>();
    CateringAdapter adapter;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_catering);

        recyclerView = findViewById(R.id.cateringRecyclerView);
        backBtn = findViewById(R.id.backBtnManageCatering);
        noBookingsTv = findViewById(R.id.noBookingsTv); // Assuming this ID exists or I'll add it

        dbRef = FirebaseDatabase.getInstance().getReference("CateringBookings");

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CateringAdapter(bookings, this);
        recyclerView.setAdapter(adapter);

        // Load catering bookings from Firebase Realtime Database
        loadCateringBookings();

        // Back button
        backBtn.setOnClickListener(v -> finish());
    }

    private void loadCateringBookings() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookings.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    CateringBooking booking = data.getValue(CateringBooking.class);
                    if (booking != null) {
                        bookings.add(booking);
                    }
                }
                
                if (bookings.isEmpty()) {
                    if (noBookingsTv != null) noBookingsTv.setVisibility(View.VISIBLE);
                } else {
                    if (noBookingsTv != null) noBookingsTv.setVisibility(View.GONE);
                }
                
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ManageCateringActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
