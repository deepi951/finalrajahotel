package com.example.rajahotel;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewCustomersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView backBtn;
    ArrayList<User> customers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);

        recyclerView = findViewById(R.id.customersRecyclerView);
        backBtn = findViewById(R.id.backBtnViewCustomers);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load customers from Firebase
        loadCustomers();

        // Back button
        backBtn.setOnClickListener(v -> finish());
    }

    private void loadCustomers() {
        // Load from Firebase (dummy data for now)
        customers.add(new User("USR001", "John Doe", "john@example.com", "9876543210",
                "123 Main St", false, "2024-01-15"));
        customers.add(new User("USR002", "Jane Smith", "jane@example.com", "9876543211",
                "456 Oak Ave", false, "2024-01-20"));

        CustomerAdapter adapter = new CustomerAdapter(customers, this);
        recyclerView.setAdapter(adapter);
    }
}

