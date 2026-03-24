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
    Button loginBtn;
    TextView registerText;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        registerText = findViewById(R.id.registerText);

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
                            if (mAuth.getCurrentUser().isEmailVerified()) {

                                Toast.makeText(this,
                                        "Login Successful 🎉",
                                        Toast.LENGTH_SHORT).show();

                                // 👉 Open Menu / Home screen
                                startActivity(new Intent(
                                        LoginActivity.this,
                                        MainActivity.class));

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

        // 👉 Go to Register page
        registerText.setOnClickListener(v ->
                startActivity(new Intent(
                        LoginActivity.this,
                        RegisterActivity.class))
        );
    }
}