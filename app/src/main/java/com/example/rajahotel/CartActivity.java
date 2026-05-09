package com.example.rajahotel;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cartRecyclerView);
        totalPrice = findViewById(R.id.totalPrice);

        CartAdapter adapter = new CartAdapter(CartManager.cartList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Calculate total price
        int total = 0;
        for (CartItem item : CartManager.cartList) {
            total += item.getFinalPrice() * item.quantity;
        }
        totalPrice.setText("Total: Rs. " + total);
    }
}
