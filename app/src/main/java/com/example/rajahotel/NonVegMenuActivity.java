package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NonVegMenuActivity extends AppCompatActivity {

    int qty1 = 1, qty2 = 1, qty3 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonveg_menu);

        Toast.makeText(this, "Non-Veg Page Opened", Toast.LENGTH_SHORT).show();

        // ===== CHICKEN BIRYANI =====
        TextView q1 = findViewById(R.id.qty1);
        Button plus1 = findViewById(R.id.plus1);
        Button minus1 = findViewById(R.id.minus1);
        Button add1 = findViewById(R.id.addCart1);

        plus1.setOnClickListener(v -> {
            qty1++;
            q1.setText(String.valueOf(qty1));
        });

        minus1.setOnClickListener(v -> {
            if (qty1 > 1) qty1--;
            q1.setText(String.valueOf(qty1));
        });

        add1.setOnClickListener(v ->
                addToCart("Chicken Biryani", 150, qty1)
        );

        // ===== MUTTON CURRY =====
        TextView q2 = findViewById(R.id.qty2);
        Button plus2 = findViewById(R.id.plus2);
        Button minus2 = findViewById(R.id.minus2);
        Button add2 = findViewById(R.id.addCart2);

        plus2.setOnClickListener(v -> {
            qty2++;
            q2.setText(String.valueOf(qty2));
        });

        minus2.setOnClickListener(v -> {
            if (qty2 > 1) qty2--;
            q2.setText(String.valueOf(qty2));
        });

        add2.setOnClickListener(v ->
                addToCart("Mutton Curry", 200, qty2)
        );

        // ===== CHICKEN 65 =====
        TextView q3 = findViewById(R.id.qty3);
        Button plus3 = findViewById(R.id.plus3);
        Button minus3 = findViewById(R.id.minus3);
        Button add3 = findViewById(R.id.addCart3);

        plus3.setOnClickListener(v -> {
            qty3++;
            q3.setText(String.valueOf(qty3));
        });

        minus3.setOnClickListener(v -> {
            if (qty3 > 1) qty3--;
            q3.setText(String.valueOf(qty3));
        });

        add3.setOnClickListener(v ->
                addToCart("Chicken 65", 120, qty3)
        );

        // VIEW CART BUTTON
        Button viewCart = findViewById(R.id.viewCartBtn);
        viewCart.setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class))
        );
    }

    private void addToCart(String name, int price, int qty) {
        Toast.makeText(this,
                name + " added (" + qty + ")",
                Toast.LENGTH_SHORT).show();
    }
}