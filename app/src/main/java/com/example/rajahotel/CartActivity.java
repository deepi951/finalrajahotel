package com.example.rajahotel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView totalPrice;
    Button placeOrderBtn;
    EditText phoneEt, addressEt;
    RadioGroup paymentRg;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    int currentTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.cartRecyclerView);
        totalPrice = findViewById(R.id.totalPrice);
        placeOrderBtn = findViewById(R.id.placeOrderBtn);
        phoneEt = findViewById(R.id.phoneEt);
        addressEt = findViewById(R.id.addressEt);
        paymentRg = findViewById(R.id.paymentRg);

        CartAdapter adapter = new CartAdapter(CartManager.cartList, this::updateTotalPrice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        updateTotalPrice();

        placeOrderBtn.setOnClickListener(v -> {
            if (CartManager.cartList.isEmpty()) {
                Toast.makeText(CartActivity.this, "Cart is empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            String phone = phoneEt.getText().toString().trim();
            String address = addressEt.getText().toString().trim();

            if (phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please enter delivery details", Toast.LENGTH_SHORT).show();
                return;
            }

            placeOrder(currentTotal, phone, address);
        });
    }

    private void updateTotalPrice() {
        currentTotal = 0;
        for (CartItem item : CartManager.cartList) {
            currentTotal += item.getFinalPrice() * item.quantity;
        }
        totalPrice.setText("Total: ₹" + currentTotal);
    }

    private void placeOrder(int totalAmount, String phone, String address) {
        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Please login to place order", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = mAuth.getCurrentUser().getUid();
        String userEmail = mAuth.getCurrentUser().getEmail();
        
        int selectedPaymentId = paymentRg.getCheckedRadioButtonId();
        RadioButton selectedPayment = findViewById(selectedPaymentId);
        String paymentMethod = selectedPayment.getText().toString();
        
        // Logical status based on payment method
        String paymentStatus = paymentMethod.contains("COD") ? "Pending" : "Success";

        db.collection("users").document(userId).get().addOnSuccessListener(documentSnapshot -> {
            String customerName = "Customer";
            if (documentSnapshot.exists()) {
                customerName = documentSnapshot.getString("name");
            }

            // Create Order ID
            String orderId = "ORD" + System.currentTimeMillis();
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            // Use the Order model structure to ensure compatibility with Admin Web Panel
            // Updated constructor call to match Order(orderId, userId, customerName, customerEmail, customerPhone, totalAmount, status, paymentStatus, paymentMethod, deliveryAddress, orderDate, items)
            Order newOrder = new Order(
                    orderId,
                    userId,
                    customerName,
                    userEmail,
                    phone,
                    totalAmount,
                    "Pending",     // status
                    paymentStatus, // paymentStatus
                    paymentMethod,
                    address,
                    currentDate,
                    new ArrayList<>(CartManager.cartList)
            );

            db.collection("orders").document(orderId).set(newOrder).addOnSuccessListener(aVoid -> {
                String successMsg = "Order placed successfully! ✅";
                if (paymentMethod.contains("COD")) {
                    successMsg += "\nStatus: Pending (Pay on Delivery)";
                } else {
                    successMsg += "\nStatus: Payment Success";
                }
                Toast.makeText(CartActivity.this, successMsg, Toast.LENGTH_LONG).show();
                CartManager.cartList.clear();
                finish();
            }).addOnFailureListener(e -> {
                Toast.makeText(CartActivity.this, "Failed to place order: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }
}
