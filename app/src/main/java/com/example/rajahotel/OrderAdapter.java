package com.example.rajahotel;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final ArrayList<Order> orders;
    private final Activity activity;

    public OrderAdapter(ArrayList<Order> orders, Activity activity) {
        this.orders = orders;
        this.activity = activity;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_admin_row, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        
        holder.orderId.setText("Order ID: " + order.orderId);
        holder.customerName.setText("Customer: " + order.userName);
        holder.totalPrice.setText("Total: Rs. " + order.totalPrice);
        holder.orderDate.setText("Date: " + order.orderDate);
        holder.orderStatus.setText(order.status);

        holder.viewDetailsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(activity, OrderDetailsActivity.class);
            intent.putExtra("order", order);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, customerName, totalPrice, orderDate, orderStatus;
        Button viewDetailsBtn;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.adminOrderId);
            customerName = itemView.findViewById(R.id.adminCustomerName);
            totalPrice = itemView.findViewById(R.id.adminTotalPrice);
            orderDate = itemView.findViewById(R.id.adminOrderDate);
            orderStatus = itemView.findViewById(R.id.adminOrderStatus);
            viewDetailsBtn = itemView.findViewById(R.id.viewOrderDetailsBtn);
        }
    }
}

