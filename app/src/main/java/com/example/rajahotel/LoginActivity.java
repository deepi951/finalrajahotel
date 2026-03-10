package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText phone;
    Button getOtpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone = findViewById(R.id.phone);
        getOtpBtn = findViewById(R.id.getOtpBtn);

        getOtpBtn.setOnClickListener(v -> {

            String number = phone.getText().toString().trim();

            if(number.isEmpty()){
                Toast.makeText(this,"Enter phone number",Toast.LENGTH_SHORT).show();
            }
            else if(!number.matches("[6-9][0-9]{9}")){
                Toast.makeText(this,"Enter valid Indian mobile number",Toast.LENGTH_SHORT).show();
            }
            else{

                Toast.makeText(this,"OTP Sent: 1234",Toast.LENGTH_LONG).show();

                Intent i = new Intent(LoginActivity.this, OtpActivity.class);
                startActivity(i);

            }

        });

    }
}