package com.example.rajahotel;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

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
        
        holder.bookingId.setText("ID: " + booking.bookingId);
        holder.customerName.setText("Name: " + booking.name);
        holder.eventType.setText("Event: " + booking.eventName);
        holder.guestCount.setText("Guests: " + booking.count);
        holder.bookingStatus.setText("Status: " + booking.status);
        holder.contactTv.setText("Phone: " + booking.phoneNumber);

        // Confirm Button Logic
        if ("Confirmed".equals(booking.status)) {
            holder.updateBtn.setVisibility(View.GONE);
        } else {
            holder.updateBtn.setVisibility(View.VISIBLE);
            holder.updateBtn.setText("Confirm Booking");
            holder.updateBtn.setOnClickListener(v -> {
                FirebaseDatabase.getInstance().getReference("CateringBookings")
                        .child(booking.bookingId)
                        .child("status")
                        .setValue("Confirmed")
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(activity, "Booking Confirmed! ✅", Toast.LENGTH_SHORT).show();
                        });
            });
        }
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    static class CateringViewHolder extends RecyclerView.ViewHolder {
        TextView bookingId, customerName, eventType, guestCount, bookingStatus, contactTv;
        Button updateBtn;

        public CateringViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingId = itemView.findViewById(R.id.cateringBookingId);
            customerName = itemView.findViewById(R.id.cateringCustomerName);
            eventType = itemView.findViewById(R.id.cateringEventType);
            guestCount = itemView.findViewById(R.id.cateringGuestCount);
            bookingStatus = itemView.findViewById(R.id.cateringStatus);
            updateBtn = itemView.findViewById(R.id.updateCateringBtn);
            // Reusing existing IDs or adding logic for contact
            contactTv = itemView.findViewById(R.id.cateringEventDate);
        }
    }
}
