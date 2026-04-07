package com.example.rajahotel;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewOrdersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView backBtn;
    ArrayList<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        recyclerView = findViewById(R.id.ordersRecyclerView);
        backBtn = findViewById(R.id.backBtnViewOrders);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load orders from Firebase
        loadOrders();

        // Back button
        backBtn.setOnClickListener(v -> finish());
    }

    private void loadOrders() {
        // Load from Firebase (dummy data for now)
        orders.add(new Order("ORD001", "USR001", "John Doe", "john@example.com", "9876543210",
                250.0, "Pending", "2024-03-30", "123 Main St", "2x Idly, 1x Dosa"));
        orders.add(new Order("ORD002", "USR002", "Jane Smith", "jane@example.com", "9876543211",
                350.0, "Preparing", "2024-03-30", "456 Oak Ave", "1x Chicken 65, 2x Meals"));

        OrderAdapter adapter = new OrderAdapter(orders, this);
        recyclerView.setAdapter(adapter);
    }
}

