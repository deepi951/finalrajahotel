package com.example.rajahotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CustomerOrderAdapter extends RecyclerView.Adapter<CustomerOrderAdapter.OrderViewHolder> {

    private final ArrayList<MapOrder> orders;

    public CustomerOrderAdapter(ArrayList<MapOrder> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_row, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        MapOrder order = orders.get(position);

        // Format timestamp
        if (order.timestamp != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
            String dateStr = sdf.format(new Date(order.timestamp));
            holder.orderDateTv.setText("Date: " + dateStr);
        }

        holder.orderStatusTv.setText(order.status);
        holder.orderTotalTv.setText("Total: ₹" + order.totalAmount);

        // Display customer details
        if (order.customerName != null) {
            holder.orderCustomerTv.setText("Customer: " + order.customerName);
        }
        String contact = "";
        if (order.customerPhone != null) contact += order.customerPhone;
        if (order.customerEmail != null) contact += " (" + order.customerEmail + ")";
        if (!contact.isEmpty()) {
            holder.orderContactTv.setText("Contact: " + contact);
        }

        // Display delivery details
        if (order.deliveryAddress != null) {
            holder.orderAddressTv.setText("Address: " + order.deliveryAddress);
        }
        if (order.paymentMethod != null) {
            holder.orderPaymentTv.setText("Payment: " + order.paymentMethod);
        }

        // Display items summary
        StringBuilder itemsSummary = new StringBuilder("Items: ");
        if (order.items != null) {
            for (int i = 0; i < order.items.size(); i++) {
                CartItem item = order.items.get(i);
                itemsSummary.append(item.name).append(" x").append(item.quantity);
                if (i < order.items.size() - 1) {
                    itemsSummary.append(", ");
                }
            }
        }
        holder.orderItemsTv.setText(itemsSummary.toString());

        // Status color coding
        if ("Pending".equalsIgnoreCase(order.status)) {
            holder.orderStatusTv.setTextColor(0xFFFF9800); // Orange
        } else if ("Delivered".equalsIgnoreCase(order.status)) {
            holder.orderStatusTv.setTextColor(0xFF4CAF50); // Green
        } else {
            holder.orderStatusTv.setTextColor(0xFF2196F3); // Blue
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDateTv, orderStatusTv, orderItemsTv, orderTotalTv, orderAddressTv, orderPaymentTv, orderCustomerTv, orderContactTv;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDateTv = itemView.findViewById(R.id.orderDateTv);
            orderStatusTv = itemView.findViewById(R.id.orderStatusTv);
            orderItemsTv = itemView.findViewById(R.id.orderItemsTv);
            orderTotalTv = itemView.findViewById(R.id.orderTotalTv);
            orderAddressTv = itemView.findViewById(R.id.orderAddressTv);
            orderPaymentTv = itemView.findViewById(R.id.orderPaymentTv);
            orderCustomerTv = itemView.findViewById(R.id.orderCustomerTv);
            orderContactTv = itemView.findViewById(R.id.orderContactTv);
        }
    }

    // Helper class to match Firestore document structure for orders
    public static class MapOrder {
        public String status;
        public Long timestamp;
        public int totalAmount;
        public String deliveryAddress;
        public String paymentMethod;
        public String customerName;
        public String customerEmail;
        public String customerPhone;
        public ArrayList<CartItem> items;

        public MapOrder() {}
    }
}
