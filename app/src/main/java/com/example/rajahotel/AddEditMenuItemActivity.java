package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class AddEditMenuItemActivity extends AppCompatActivity {

    EditText itemNameEt, itemPriceEt, itemDiscountEt, itemDescEt;
    RadioGroup categoryRg;
    SwitchCompat availabilitySwitch;
    Button saveBtn, deleteBtn;
    ImageView backBtn;

    String itemId = null;
    MenuItem currentItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_menu_item);

        // Initialize views
        itemNameEt = findViewById(R.id.itemNameEt);
        itemPriceEt = findViewById(R.id.itemPriceEt);
        itemDiscountEt = findViewById(R.id.itemDiscountEt);
        itemDescEt = findViewById(R.id.itemDescEt);
        categoryRg = findViewById(R.id.categoryRg);
        availabilitySwitch = findViewById(R.id.availabilitySwitch);
        saveBtn = findViewById(R.id.saveBtnMenuItem);
        deleteBtn = findViewById(R.id.deleteBtnMenuItem);
        backBtn = findViewById(R.id.backBtnMenuItem);

        // Get intent data for edit
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("item")) {
            // Edit mode
            currentItem = (MenuItem) intent.getSerializableExtra("item");
            if (currentItem != null) {
                itemId = currentItem.itemId;
                populateFields();
                deleteBtn.setVisibility(View.VISIBLE);
            }
        } else {
            // Add mode
            deleteBtn.setVisibility(View.GONE);
        }

        // Save button
        saveBtn.setOnClickListener(v -> saveMenuItem());

        // Delete button
        deleteBtn.setOnClickListener(v -> deleteMenuItem());

        // Back button
        backBtn.setOnClickListener(v -> finish());
    }

    private void populateFields() {
        if (currentItem == null) return;
        itemNameEt.setText(currentItem.name);
        itemPriceEt.setText(String.valueOf(currentItem.price));
        itemDiscountEt.setText(String.valueOf(currentItem.discount));
        itemDescEt.setText(currentItem.description);
        
        // Fixed: Use isAvailable field from MenuItem class
        availabilitySwitch.setChecked(currentItem.isAvailable);

        if (currentItem.category != null && currentItem.category.equals("Veg")) {
            categoryRg.check(R.id.vegRadio);
        } else {
            categoryRg.check(R.id.nonVegRadio);
        }
    }

    private void saveMenuItem() {
        String name = itemNameEt.getText().toString().trim();
        String priceStr = itemPriceEt.getText().toString().trim();
        String discountStr = itemDiscountEt.getText().toString().trim();
        String description = itemDescEt.getText().toString().trim();
        int selectedId = categoryRg.getCheckedRadioButtonId();
        boolean available = availabilitySwitch.isChecked();

        if (name.isEmpty() || priceStr.isEmpty() || description.isEmpty() || selectedId == -1) {
            Toast.makeText(this, "Please fill all fields ❌", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int price = Integer.parseInt(priceStr);
            int discount = discountStr.isEmpty() ? 0 : Integer.parseInt(discountStr);
            String category = selectedId == R.id.vegRadio ? "Veg" : "Non-Veg";

            if (itemId == null) {
                itemId = String.valueOf(System.currentTimeMillis());
            }

            // Preserving existing imageUrl if editing
            String imageUrl = (currentItem != null) ? currentItem.imageUrl : "";

            currentItem = new MenuItem(
                    itemId,
                    name,
                    price,
                    category,
                    description,
                    imageUrl,
                    discount,
                    available
            );

            // Save to Firebase (replace with actual Firebase code)
            Toast.makeText(this, "Menu item saved successfully ✅", Toast.LENGTH_SHORT).show();
            finish();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format ❌", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteMenuItem() {
        if (itemId != null) {
            // Delete from Firebase
            Toast.makeText(this, "Menu item deleted ✅", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
