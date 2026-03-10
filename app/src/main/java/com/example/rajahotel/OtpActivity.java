package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OtpActivity extends AppCompatActivity {

    EditText otp;
    Button verifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otp = findViewById(R.id.otp);
        verifyBtn = findViewById(R.id.verifyBtn);

        verifyBtn.setOnClickListener(v -> {

            String userOtp = otp.getText().toString();

            if(userOtp.equals("1234")){
                Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OtpActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(this,"Invalid OTP",Toast.LENGTH_SHORT).show();
            }

        });

    }
}