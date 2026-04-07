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

public class CateringAdapter extends RecyclerView.Adapter<CateringAdapter.CateringViewHolder> {

    private final ArrayList<CateringBooking> bookings;
    private final Activity activity;

    public CateringAdapter(ArrayList<CateringBooking> bookings, Activity activity) {
        this.bookings = bookings;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CateringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.catering_booking_row, parent, false);
        return new CateringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CateringViewHolder holder, int position) {
        CateringBooking booking = bookings.get(position);
        
        holder.bookingId.setText("Booking ID: " + booking.bookingId);
        holder.customerName.setText("Customer: " + booking.userName);
        holder.eventType.setText("Event: " + booking.eventType);
        holder.eventDate.setText("Date: " + booking.eventDate);
        holder.guestCount.setText("Guests: " + booking.guestCount);
        holder.estimatedCost.setText("Cost: Rs. " + booking.estimatedCost);
        holder.bookingStatus.setText("Status: " + booking.status);

        holder.updateBtn.setOnClickListener(v -> {
            // Open dialog to update status
        });
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    static class CateringViewHolder extends RecyclerView.ViewHolder {
        TextView bookingId, customerName, eventType, eventDate, guestCount, estimatedCost, bookingStatus;
        Button updateBtn;

        public CateringViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingId = itemView.findViewById(R.id.cateringBookingId);
            customerName = itemView.findViewById(R.id.cateringCustomerName);
            eventType = itemView.findViewById(R.id.cateringEventType);
            eventDate = itemView.findViewById(R.id.cateringEventDate);
            guestCount = itemView.findViewById(R.id.cateringGuestCount);
            estimatedCost = itemView.findViewById(R.id.cateringEstimatedCost);
            bookingStatus = itemView.findViewById(R.id.cateringStatus);
            updateBtn = itemView.findViewById(R.id.updateCateringBtn);
        }
    }
}

