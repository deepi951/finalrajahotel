package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboardActivity extends AppCompatActivity {

    Button manageMenuBtn, viewOrdersBtn, manageCateringBtn, viewCustomersBtn, logoutBtn;
    ImageView backBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        manageMenuBtn = findViewById(R.id.manageMenuBtn);
        viewOrdersBtn = findViewById(R.id.viewOrdersBtn);
        manageCateringBtn = findViewById(R.id.manageCateringBtn);
        viewCustomersBtn = findViewById(R.id.viewCustomersBtn);
        logoutBtn = findViewById(R.id.adminLogoutBtn);
        backBtn = findViewById(R.id.adminBackBtn);

        // Manage Menu Items
        manageMenuBtn.setOnClickListener(v ->
            startActivity(new Intent(AdminDashboardActivity.this, ManageMenuActivity.class))
        );

        // View Orders
        viewOrdersBtn.setOnClickListener(v ->
            startActivity(new Intent(AdminDashboardActivity.this, ViewOrdersActivity.class))
        );

        // Manage Catering Bookings
        manageCateringBtn.setOnClickListener(v ->
            startActivity(new Intent(AdminDashboardActivity.this, ManageCateringActivity.class))
        );

        // View Customer Details
        viewCustomersBtn.setOnClickListener(v ->
            startActivity(new Intent(AdminDashboardActivity.this, ViewCustomersActivity.class))
        );

        // Logout
        logoutBtn.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(this, "Logged out 👋", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AdminDashboardActivity.this, LoginActivity.class));
            finish();
        });

        // Back button
        backBtn.setOnClickListener(v -> finish());
    }
}

