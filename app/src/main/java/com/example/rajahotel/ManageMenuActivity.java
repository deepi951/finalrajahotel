package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ManageMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button addNewItemBtn;
    ImageView backBtn;
    ArrayList<MenuItem> menuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_menu);

        recyclerView = findViewById(R.id.menuItemsRecyclerView);
        addNewItemBtn = findViewById(R.id.addNewMenuItemBtn);
        backBtn = findViewById(R.id.backBtnManageMenu);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add sample menu items (load from Firebase in real app)
        loadMenuItems();

        // Add New Item
        addNewItemBtn.setOnClickListener(v ->
            startActivity(new Intent(ManageMenuActivity.this, AddEditMenuItemActivity.class))
        );

        // Back button
        backBtn.setOnClickListener(v -> finish());
    }

    private void loadMenuItems() {
        // Load from Firebase (for now, using dummy data)
        menuItems.add(new MenuItem("1", "Idly", 10, "Veg", "South Indian breakfast", true));
        menuItems.add(new MenuItem("2", "Dosa", 20, "Veg", "Crispy rice crepe", true));
        menuItems.add(new MenuItem("3", "Chicken 65", 120, "Non-Veg", "Spicy chicken fry", true));

        MenuItemAdapter adapter = new MenuItemAdapter(menuItems, this);
        recyclerView.setAdapter(adapter);
    }
}

