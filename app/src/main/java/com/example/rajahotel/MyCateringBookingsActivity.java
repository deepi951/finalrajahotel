package com.example.rajahotel;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyCateringBookingsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView backBtn;
    ArrayList<CateringBooking> myBookings = new ArrayList<>();
    CateringAdapter adapter;

    FirebaseAuth mAuth;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_catering_bookings);

        recyclerView = findViewById(R.id.myCateringRecyclerView);
        backBtn = findViewById(R.id.backBtnMyCatering);

        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("CateringBookings");

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CateringAdapter(myBookings, this);
        recyclerView.setAdapter(adapter);

        // Load user's catering bookings
        loadMyCateringBookings();

        // Back button
        backBtn.setOnClickListener(v -> finish());
    }

    private void loadMyCateringBookings() {
        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Please login to view your bookings", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String userId = mAuth.getCurrentUser().getUid();
        Toast.makeText(this, "Loading bookings for user: " + userId, Toast.LENGTH_SHORT).show();

        // Load all bookings and filter by userId
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myBookings.clear();
                int totalBookings = 0;
                int myBookingsCount = 0;

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CateringBooking booking = dataSnapshot.getValue(CateringBooking.class);
                    totalBookings++;
                    
                    if (booking != null) {
                        // Add all bookings to see data (for debugging)
                        myBookings.add(booking);
                        myBookingsCount++;
                    }
                }

                adapter.notifyDataSetChanged();

                Toast.makeText(MyCateringBookingsActivity.this, 
                        "Total bookings: " + totalBookings + "\nYour bookings: " + myBookingsCount, 
                        Toast.LENGTH_LONG).show();

                if (myBookings.isEmpty()) {
                    Toast.makeText(MyCateringBookingsActivity.this, 
                            "No catering bookings found. Submit an enquiry first!", 
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyCateringBookingsActivity.this, 
                        "Error loading bookings: " + error.getMessage(), 
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
