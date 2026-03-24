package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText fullName, email, password;
    Button registerBtn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.registerBtn);

        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(v -> {

            String name = fullName.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            // Validation
            if (name.isEmpty()) {
                fullName.setError("Enter Full Name");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                email.setError("Enter Valid Email");
                return;
            }

            if (pass.length() < 6) {
                password.setError("Password must be 6+ characters");
                return;
            }

            // 🔥 Create account in Firebase
            mAuth.createUserWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {

                            // 📧 Send REAL verification email
                            mAuth.getCurrentUser()
                                    .sendEmailVerification();

                            Toast.makeText(this,
                                    "Registration Successful! Check email 📧",
                                    Toast.LENGTH_LONG).show();

                            startActivity(new Intent(
                                    RegisterActivity.this,
                                    LoginActivity.class));

                            finish();

                        } else {

                            Toast.makeText(this,
                                    "Error: " +
                                            task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
        });
    }
}