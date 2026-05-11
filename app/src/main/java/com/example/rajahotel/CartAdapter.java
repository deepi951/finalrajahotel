package com.example.rajahotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final ArrayList<CartItem> cartItems;
    private final OnItemRemovedListener listener;

    // Interface to communicate back to Activity
    public interface OnItemRemovedListener {
        void onItemRemoved();
    }

    public CartAdapter(ArrayList<CartItem> cartItems, OnItemRemovedListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item_row, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        Context context = holder.itemView.getContext();
        holder.itemName.setText(item.name);
        
        int finalPrice = item.getFinalPrice();
        holder.itemPrice.setText("Rs. " + finalPrice);

        holder.itemQty.setText("Quantity: " + item.quantity);

        // --- UPDATED IMAGE LOADING LOGIC ---
        // Try to get resource ID from drawable folder using the imageUrl string
        int imageResId = context.getResources().getIdentifier(item.imageUrl, "drawable", context.getPackageName());

        if (imageResId != 0) {
            Glide.with(context)
                    .load(imageResId)
                    .placeholder(R.drawable.hotel_bg)
                    .into(holder.itemImage);
        } else {
            Glide.with(context)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.hotel_bg)
                    .into(holder.itemImage);
        }
        // ------------------------------------

        // Remove button logic
        holder.removeBtn.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) {
                String removedItemName = cartItems.get(currentPosition).name;
                cartItems.remove(currentPosition);
                notifyItemRemoved(currentPosition);
                notifyItemRangeChanged(currentPosition, cartItems.size());
                
                Toast.makeText(v.getContext(), removedItemName + " removed from cart", Toast.LENGTH_SHORT).show();
                
                // Notify Activity to update total price
                if (listener != null) {
                    listener.onItemRemoved();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage, removeBtn;
        TextView itemName, itemPrice, itemQty;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemQty = itemView.findViewById(R.id.itemQty);
            removeBtn = itemView.findViewById(R.id.removeBtn);
        }
    }
}
