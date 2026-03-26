package com.example.rajahotel;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {

    TextView cartText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartText = findViewById(R.id.cartText);

        StringBuilder data = new StringBuilder();
        int total = 0;

        for (CartItem item : CartManager.cartList) {

            int itemTotal = item.price * item.quantity;

            data.append("🍽 ")
                    .append(item.name)
                    .append("\nQty: ")
                    .append(item.quantity)
                    .append("\nPrice: Rs.")
                    .append(itemTotal)
                    .append("\n\n");

            total += itemTotal;
        }

        data.append("💰 Total: Rs. ").append(total);

        cartText.setText(data.toString());
    }
}