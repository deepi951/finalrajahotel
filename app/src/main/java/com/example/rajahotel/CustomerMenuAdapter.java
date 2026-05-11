package com.example.rajahotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomerMenuAdapter extends RecyclerView.Adapter<CustomerMenuAdapter.CustomerViewHolder> {

    private final Context context;
    private final ArrayList<MenuItem> menuItems;

    public CustomerMenuAdapter(Context context, ArrayList<MenuItem> menuItems) {
        this.context = context;
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customer_menu_item_row, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);

        holder.itemName.setText(item.name);
        holder.itemDescription.setText(item.description);
        
        int finalPrice = item.getFinalPrice();
        holder.itemPrice.setText("₹" + finalPrice);

        if (item.discount > 0) {
            holder.itemDiscount.setVisibility(View.VISIBLE);
            holder.itemDiscount.setText(item.discount + "% OFF");
        } else {
            holder.itemDiscount.setVisibility(View.GONE);
        }

        // --- UPDATED IMAGE LOADING LOGIC ---
        // Try to get resource ID from drawable folder using the imageUrl string
        int imageResId = context.getResources().getIdentifier(item.imageUrl, "drawable", context.getPackageName());

        if (imageResId != 0) {
            // Load from local drawable
            Glide.with(context)
                    .load(imageResId)
                    .placeholder(R.drawable.hotel_bg)
                    .into(holder.itemImage);
        } else {
            // If not found in drawables, try loading as a URL
            Glide.with(context)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.hotel_bg)
                    .into(holder.itemImage);
        }
        // ------------------------------------

        // Quantity logic
        final int[] qty = {1};
        holder.qtyTv.setText(String.valueOf(qty[0]));

        holder.plusBtn.setOnClickListener(v -> {
            qty[0]++;
            holder.qtyTv.setText(String.valueOf(qty[0]));
        });

        holder.minusBtn.setOnClickListener(v -> {
            if (qty[0] > 1) {
                qty[0]--;
                holder.qtyTv.setText(String.valueOf(qty[0]));
            }
        });

        holder.addCartBtn.setOnClickListener(v -> {
            CartItem cartItem = new CartItem(item.name, item.price, item.discount, qty[0], item.imageUrl);
            CartManager.cartList.add(cartItem);
            Toast.makeText(context, item.name + " added to cart 🛒", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    static class CustomerViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemPrice, itemDiscount, itemDescription, qtyTv;
        Button plusBtn, minusBtn, addCartBtn;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemDiscount = itemView.findViewById(R.id.itemDiscount);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            qtyTv = itemView.findViewById(R.id.qtyTv);
            plusBtn = itemView.findViewById(R.id.plusBtn);
            minusBtn = itemView.findViewById(R.id.minusBtn);
            addCartBtn = itemView.findViewById(R.id.addCartBtn);
        }
    }
}
