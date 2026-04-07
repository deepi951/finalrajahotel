package com.example.rajahotel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private final ArrayList<User> customers;
    private final Activity activity;

    public CustomerAdapter(ArrayList<User> customers, Activity activity) {
        this.customers = customers;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customer_row, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        User customer = customers.get(position);
        
        holder.customerName.setText("Name: " + customer.fullName);
        holder.customerEmail.setText("Email: " + customer.email);
        holder.customerPhone.setText("Phone: " + customer.phone);
        holder.customerAddress.setText("Address: " + customer.address);
        holder.joinDate.setText("Joined: " + customer.createdDate);

        holder.viewDetailsBtn.setOnClickListener(v -> {
            // Open customer details
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    static class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView customerName, customerEmail, customerPhone, customerAddress, joinDate;
        Button viewDetailsBtn;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.customerNameRow);
            customerEmail = itemView.findViewById(R.id.customerEmailRow);
            customerPhone = itemView.findViewById(R.id.customerPhoneRow);
            customerAddress = itemView.findViewById(R.id.customerAddressRow);
            joinDate = itemView.findViewById(R.id.customerJoinDate);
            viewDetailsBtn = itemView.findViewById(R.id.viewCustomerDetailsBtn);
        }
    }
}

