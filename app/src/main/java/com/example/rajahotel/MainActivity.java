package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button vegBtn, nonVegBtn, cartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons
        vegBtn = findViewById(R.id.vegMenuBtn);
        nonVegBtn = findViewById(R.id.nonVegMenuBtn);
        cartBtn = findViewById(R.id.viewCartBtn);

        // Veg Menu
        vegBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, VegMenuActivity.class));
        });

        // Non Veg Menu
        nonVegBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, NonVegMenuActivity.class));
        });

        // View Cart
        cartBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
        });
    }
}