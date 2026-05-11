package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class NonVegMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomerMenuAdapter adapter;
    ArrayList<MenuItem> itemList;
    FirebaseFirestore db;
    Button viewCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nonveg_menu);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.nonVegRecyclerView);
        viewCartBtn = findViewById(R.id.viewCartBtn);

        itemList = new ArrayList<>();
        adapter = new CustomerMenuAdapter(this, itemList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadNonVegMenuRealtime();

        viewCartBtn.setOnClickListener(v -> {
            startActivity(new Intent(NonVegMenuActivity.this, CartActivity.class));
        });
    }

    private void loadNonVegMenuRealtime() {
        // Updated: Query "menu" collection and filter by category "Non-Veg"
        db.collection("menu")
                .whereEqualTo("category", "Non-Veg")
                .whereEqualTo("isAvailable", true)
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        Toast.makeText(this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (value != null) {
                        itemList.clear();
                        for (QueryDocumentSnapshot document : value) {
                            MenuItem item = document.toObject(MenuItem.class);
                            item.itemId = document.getId();
                            itemList.add(item);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
