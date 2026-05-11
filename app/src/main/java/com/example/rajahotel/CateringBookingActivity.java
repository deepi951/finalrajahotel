package com.example.rajahotel;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CateringBookingActivity extends AppCompatActivity {

    EditText nameEt, emailEt, eventNameEt, phoneEt, altPhoneEt, guestCountEt, locationEt, requirementsEt;
    RadioGroup menuTypeRg;
    TextView sampleItemsTv, contactInfoTv;
    Button submitBtn;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_booking);

        nameEt = findViewById(R.id.userNameEt);
        emailEt = findViewById(R.id.userEmailEt);
        eventNameEt = findViewById(R.id.eventNameEt);
        phoneEt = findViewById(R.id.phoneEt);
        altPhoneEt = findViewById(R.id.altPhoneEt);
        guestCountEt = findViewById(R.id.guestCountEt);
        locationEt = findViewById(R.id.locationEt);
        requirementsEt = findViewById(R.id.requirementsEt);
        menuTypeRg = findViewById(R.id.menuTypeRg);
        sampleItemsTv = findViewById(R.id.sampleItemsTv);
        contactInfoTv = findViewById(R.id.contactInfoTv);
        submitBtn = findViewById(R.id.submitBtn);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        menuTypeRg.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            if (rb != null) displaySampleItems(rb.getText().toString());
        });

        submitBtn.setOnClickListener(v -> submitEnquiry());
    }

    private void displaySampleItems(String menuType) {
        StringBuilder items = new StringBuilder("Sample " + menuType + " Items:\n");
        if (menuType.equals("Veg")) {
            items.append("- Paneer Butter Masala: Rs. 250\n- Veg Biryani: Rs. 200\n- Mixed Veg Curry: Rs. 180");
        } else {
            items.append("- Chicken Curry: Rs. 300\n- Mutton Biryani: Rs. 350\n- Fish Fry: Rs. 250");
        }
        sampleItemsTv.setText(items.toString());
    }

    private void submitEnquiry() {
        if (mAuth.getCurrentUser() == null) {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = nameEt.getText().toString().trim();
        String mail = emailEt.getText().toString().trim();
        String event = eventNameEt.getText().toString().trim();
        String phone = phoneEt.getText().toString().trim();
        String guestStr = guestCountEt.getText().toString().trim();

        if (name.isEmpty() || event.isEmpty() || phone.isEmpty() || guestStr.isEmpty()) {
            Toast.makeText(this, "Please fill required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedRb = findViewById(menuTypeRg.getCheckedRadioButtonId());
        String menuType = (selectedRb != null) ? selectedRb.getText().toString() : "Veg";
        
        String bookingId = "CAT" + System.currentTimeMillis();
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        // Use the updated CateringBooking class that matches the web panel
        CateringBooking booking = new CateringBooking(
                bookingId, mAuth.getCurrentUser().getUid(), name, mail, phone, 
                altPhoneEt.getText().toString(), event, date, Integer.parseInt(guestStr), 
                menuType, locationEt.getText().toString(), requirementsEt.getText().toString(),
                0.0, "Enquiry"
        );

        // SAVE TO FIRESTORE (catering collection)
        db.collection("catering").document(bookingId).set(booking)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "✅ Enquiry submitted!", Toast.LENGTH_LONG).show();
                    finish();
                });
    }
}
