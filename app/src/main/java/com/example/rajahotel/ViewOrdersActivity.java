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
        // Fixed constructor calls to match Order(String, String, String, String, String, int, String, String, String, String, String, ArrayList<CartItem>)
        orders.add(new Order("ORD001", "USR001", "John Doe", "john@example.com", "9876543210",
                250, "Pending", "Unpaid", "COD", "123 Main St", "2024-03-30", new ArrayList<CartItem>()));
        orders.add(new Order("ORD002", "USR002", "Jane Smith", "jane@example.com", "9876543211",
                350, "Preparing", "Paid", "COD", "456 Oak Ave", "2024-03-30", new ArrayList<CartItem>()));

        OrderAdapter adapter = new OrderAdapter(orders, this);
        recyclerView.setAdapter(adapter);
    }
}
