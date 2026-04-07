package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class OrderDetailsActivity extends AppCompatActivity {

    TextView orderIdTv, customerNameTv, customerEmailTv, customerPhoneTv;
    TextView deliveryAddressTv, totalPriceTv, orderDateTv, itemsDetailsTv;
    Spinner statusSpinner;
    Button updateStatusBtn, backBtn;
    
    Order currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // Initialize views
        orderIdTv = findViewById(R.id.orderIdDetails);
        customerNameTv = findViewById(R.id.customerNameDetails);
        customerEmailTv = findViewById(R.id.customerEmailDetails);
        customerPhoneTv = findViewById(R.id.customerPhoneDetails);
        deliveryAddressTv = findViewById(R.id.deliveryAddressDetails);
        totalPriceTv = findViewById(R.id.totalPriceDetails);
        orderDateTv = findViewById(R.id.orderDateDetails);
        itemsDetailsTv = findViewById(R.id.itemsDetails);
        statusSpinner = findViewById(R.id.statusSpinner);
        updateStatusBtn = findViewById(R.id.updateStatusBtn);
        backBtn = findViewById(R.id.backBtnOrderDetails);

        // Get order from intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("order")) {
            currentOrder = (Order) intent.getSerializableExtra("order");
            displayOrderDetails();
        }

        // Setup status spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.order_status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        // Update status button
        updateStatusBtn.setOnClickListener(v -> updateOrderStatus());

        // Back button
        backBtn.setOnClickListener(v -> finish());
    }

    private void displayOrderDetails() {
        orderIdTv.setText("Order ID: " + currentOrder.orderId);
        customerNameTv.setText("Name: " + currentOrder.userName);
        customerEmailTv.setText("Email: " + currentOrder.userEmail);
        customerPhoneTv.setText("Phone: " + currentOrder.userPhone);
        deliveryAddressTv.setText("Address: " + currentOrder.deliveryAddress);
        totalPriceTv.setText("Total: Rs. " + currentOrder.totalPrice);
        orderDateTv.setText("Date: " + currentOrder.orderDate);
        itemsDetailsTv.setText("Items:\n" + currentOrder.items);

        // Set current status in spinner
        int statusIndex = Arrays.asList(getResources().getStringArray(R.array.order_status_array))
                .indexOf(currentOrder.status);
        if (statusIndex >= 0) {
            statusSpinner.setSelection(statusIndex);
        }
    }

    private void updateOrderStatus() {
        String newStatus = statusSpinner.getSelectedItem().toString();
        // Update in Firebase
        Toast.makeText(this, "Order status updated to " + newStatus + " ✅", Toast.LENGTH_SHORT).show();
        finish();
    }
}

