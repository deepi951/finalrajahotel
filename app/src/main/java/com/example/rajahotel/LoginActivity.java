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

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn, googleSignInBtn;
    TextView registerText, forgotPasswordText;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        googleSignInBtn = findViewById(R.id.googleSignInBtn);
        registerText = findViewById(R.id.registerText);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);

        mAuth = FirebaseAuth.getInstance();


        // 🔐 LOGIN BUTTON
        loginBtn.setOnClickListener(v -> {

            String userEmail = email.getText().toString().trim();
            String userPass = password.getText().toString().trim();

            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                email.setError("Enter valid email");
                return;
            }

            if (userPass.length() < 6) {
                password.setError("Password must be 6+ characters");
                return;
            }

            // 🔥 Firebase Login
            mAuth.signInWithEmailAndPassword(userEmail, userPass)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {

                            // ✅ Check email verification
                            if (mAuth.getCurrentUser() != null && mAuth.getCurrentUser().isEmailVerified()) {

                                Toast.makeText(this,
                                        "Login Successful 🎉",
                                        Toast.LENGTH_SHORT).show();

                                // 🔐 Check if user is ADMIN
                                if (AdminConfig.isAdminEmail(userEmail)) {
                                    // Open Admin Dashboard
                                    startActivity(new Intent(
                                            LoginActivity.this,
                                            AdminDashboardActivity.class));
                                } else {
                                    // Open User Menu
                                    startActivity(new Intent(
                                            LoginActivity.this,
                                            MainActivity.class));
                                }

                                finish();

                            } else {

                                Toast.makeText(this,
                                        "Verify your email first 📧",
                                        Toast.LENGTH_LONG).show();
                            }

                        } else {

                            Toast.makeText(this,
                                    "Invalid Email or Password ❌",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // 👉 Long press on login button for admin access (hidden feature)
        loginBtn.setOnLongClickListener(v -> {
            Toast.makeText(this, "Admin Access 🔐", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, AdminLoginActivity.class));
            return true;
        });

        // 👉 Go to Register page
        registerText.setOnClickListener(v ->
                startActivity(new Intent(
                        LoginActivity.this,
                        RegisterActivity.class))
        );

        // 👉 Forgot password?
        forgotPasswordText.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            if (userEmail.isEmpty()) {
                email.setError("Enter your registered email first");
                return;
            }

            // 🔥 Firebase password reset
            mAuth.sendPasswordResetEmail(userEmail)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this,
                                    "Reset link sent to your email 📧",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this,
                                    "Error! Reset link not sent ❌",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });

        // 🔗 Google Sign-in Button
        googleSignInBtn.setOnClickListener(v -> {
            Toast.makeText(this,
                    "Google Sign-in coming soon! 🚀",
                    Toast.LENGTH_SHORT).show();
            // TODO: Implement Google Sign-in Integration
        });
    }
}