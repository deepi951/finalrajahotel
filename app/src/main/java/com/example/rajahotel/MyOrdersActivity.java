package com.example.rajahotel;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MyOrdersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomerOrderAdapter adapter;
    ArrayList<CustomerOrderAdapter.MapOrder> orderList;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    TextView noOrdersTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.ordersRecyclerView);
        noOrdersTv = findViewById(R.id.noOrdersTv);

        orderList = new ArrayList<>();
        adapter = new CustomerOrderAdapter(orderList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        loadMyOrders();
    }

    private void loadMyOrders() {
        if (mAuth.getCurrentUser() == null) return;

        String userId = mAuth.getCurrentUser().getUid();

        db.collection("orders")
                .whereEqualTo("userId", userId)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        orderList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            CustomerOrderAdapter.MapOrder order = document.toObject(CustomerOrderAdapter.MapOrder.class);
                            orderList.add(order);
                        }

                        if (orderList.isEmpty()) {
                            noOrdersTv.setVisibility(View.VISIBLE);
                        } else {
                            noOrdersTv.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error loading orders: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
