package com.example.rajahotel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ManageMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    Button addNewItemBtn;

    ImageView backBtn;

    ArrayList<MenuItem> menuItems =
            new ArrayList<>();

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_manage_menu
        );

        /* =====================================
           FIREBASE
        ===================================== */

        db = FirebaseFirestore.getInstance();

        /* =====================================
           VIEWS
        ===================================== */

        recyclerView =
                findViewById(
                        R.id.menuItemsRecyclerView
                );

        addNewItemBtn =
                findViewById(
                        R.id.addNewMenuItemBtn
                );

        backBtn =
                findViewById(
                        R.id.backBtnManageMenu
                );

        /* =====================================
           RECYCLERVIEW
        ===================================== */

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        /* =====================================
           LOAD MENU ITEMS
        ===================================== */

        loadMenuItems();

        /* =====================================
           ADD NEW ITEM BUTTON
        ===================================== */

        addNewItemBtn.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            ManageMenuActivity.this,
                            AddEditMenuItemActivity.class
                    )
            );
        });

        /* =====================================
           BACK BUTTON
        ===================================== */

        backBtn.setOnClickListener(v -> finish());
    }

    /* =========================================
       LOAD MENU ITEMS FROM FIRESTORE
    ========================================= */

    private void loadMenuItems() {

        db.collection("menu")

                .get()

                .addOnSuccessListener(
                        queryDocumentSnapshots -> {

                            menuItems.clear();

                            for (DocumentSnapshot doc :
                                    queryDocumentSnapshots) {

                                MenuItem item =
                                        doc.toObject(
                                                MenuItem.class
                                        );

                                if (item != null) {

                                    item.itemId =
                                            doc.getId();

                                    menuItems.add(item);
                                }
                            }

                            MenuItemAdapter adapter =
                                    new MenuItemAdapter(
                                            menuItems,
                                            this
                                    );

                            recyclerView.setAdapter(
                                    adapter
                            );
                        })

                .addOnFailureListener(e -> {

                    Toast.makeText(
                            this,
                            "Failed to load menu",
                            Toast.LENGTH_SHORT
                    ).show();
                });
    }

    /* =========================================
       REFRESH MENU WHEN RETURNING
    ========================================= */

    @Override
    protected void onResume() {

        super.onResume();

        loadMenuItems();
    }
}