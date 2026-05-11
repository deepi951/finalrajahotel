package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText fullName, email, password, phone;
    Button registerBtn;

    FirebaseAuth mAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.registerBtn);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        registerBtn.setOnClickListener(v -> {

            String name = fullName.getText().toString().trim();
            String mail = email.getText().toString().trim();
            String phoneNumber = phone.getText().toString().trim();
            String pass = password.getText().toString().trim();

            // NAME VALIDATION
            if (name.isEmpty()) {
                fullName.setError("Enter Full Name");
                return;
            }

            // EMAIL VALIDATION
            if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                email.setError("Enter Valid Email");
                return;
            }

            // PHONE VALIDATION
            if (phoneNumber.isEmpty() || phoneNumber.length() < 10) {
                phone.setError("Enter Valid Phone Number");
                return;
            }

            // ADMIN BLOCK
            if (AdminConfig.isAdminEmail(mail)) {
                Toast.makeText(this, "This Email Reserved For Admin", Toast.LENGTH_LONG).show();
                return;
            }

            // PASSWORD VALIDATION
            if (pass.length() < 6) {
                password.setError("Password Must Be 6+ Characters");
                return;
            }

            // CREATE ACCOUNT
            mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    String uid = mAuth.getCurrentUser().getUid();

                    // SEND EMAIL VERIFICATION
                    mAuth.getCurrentUser().sendEmailVerification();

                    // USER DATA
                    HashMap<String, Object> userMap = new HashMap<>();
                    userMap.put("name", name);
                    userMap.put("email", mail);
                    userMap.put("phone", phoneNumber);
                    userMap.put("verified", false);

                    // SAVE TO FIRESTORE
                    firestore.collection("users")
                            .document(uid)
                            .set(userMap)
                            .addOnSuccessListener(unused -> {
                                Toast.makeText(this, "Registered Successfully. Verify Email.", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            });

                } else {
                    Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
