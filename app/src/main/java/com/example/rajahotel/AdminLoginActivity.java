package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn;
    TextView backText;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        email = findViewById(R.id.adminEmail);
        password = findViewById(R.id.adminPassword);
        loginBtn = findViewById(R.id.adminLoginBtn);
        backText = findViewById(R.id.backToLogin);

        mAuth = FirebaseAuth.getInstance();

        // Admin Login
        loginBtn.setOnClickListener(v -> {
            String emailStr = email.getText().toString();
            String passwordStr = password.getText().toString();

            if (emailStr.isEmpty() || passwordStr.isEmpty()) {
                Toast.makeText(this, "Please fill all fields ❌", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
                Toast.makeText(this, "Invalid email format ❌", Toast.LENGTH_SHORT).show();
                return;
            }

            // Login with Firebase
            mAuth.signInWithEmailAndPassword(emailStr, passwordStr)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            if (mAuth.getCurrentUser() != null && mAuth.getCurrentUser().isEmailVerified()) {
                                // Check if user is admin (in real app, check Firebase Realtime DB)
                                Toast.makeText(this, "Admin Login Successful 🎉", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AdminLoginActivity.this, AdminDashboardActivity.class));
                                finish();
                            } else {
                                Toast.makeText(this, "Verify your email first 📧", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(this, "Invalid Email or Password ❌", Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // Go back to login
        backText.setOnClickListener(v ->
                startActivity(new Intent(AdminLoginActivity.this, LoginActivity.class))
        );
    }
}

