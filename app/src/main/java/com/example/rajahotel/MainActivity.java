package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button vegBtn = findViewById(R.id.vegMenuBtn);

        vegBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, VegMenuActivity.class);
            startActivity(i);
        });
    }
}