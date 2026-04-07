package com.example.rajahotel;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuViewHolder> {

    private final ArrayList<MenuItem> menuItems;
    private final Activity activity;

    public MenuItemAdapter(ArrayList<MenuItem> menuItems, Activity activity) {
        this.menuItems = menuItems;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_item_admin_row, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);
        
        holder.itemName.setText(item.name);
        holder.itemPrice.setText("Rs. " + item.price);
        holder.itemCategory.setText(item.category);
        holder.itemStatus.setText(item.available ? "Available" : "Unavailable");

        holder.editBtn.setOnClickListener(v -> {
            Intent intent = new Intent(activity, AddEditMenuItemActivity.class);
            intent.putExtra("item", item);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemCategory, itemStatus;
        Button editBtn;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.adminItemName);
            itemPrice = itemView.findViewById(R.id.adminItemPrice);
            itemCategory = itemView.findViewById(R.id.adminItemCategory);
            itemStatus = itemView.findViewById(R.id.adminItemStatus);
            editBtn = itemView.findViewById(R.id.editMenuItemBtn);
        }
    }
}

