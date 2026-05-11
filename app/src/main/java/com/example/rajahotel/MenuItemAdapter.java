package com.example.rajahotel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MenuViewHolder> {

    private final ArrayList<MenuItem> menuItems;

    private final Activity activity;

    public MenuItemAdapter(
            ArrayList<MenuItem> menuItems,
            Activity activity
    ) {

        this.menuItems = menuItems;

        this.activity = activity;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(
                                R.layout.menu_item_admin_row,
                                parent,
                                false
                        );

        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull MenuViewHolder holder,
            int position
    ) {

        MenuItem item =
                menuItems.get(position);

        /* =====================================
           TEXT DATA
        ===================================== */

        holder.itemName.setText(
                item.name
        );

        // Use getFinalPrice helper from MenuItem
        int finalPrice = item.getFinalPrice();

        holder.itemPrice.setText(
                "₹" + finalPrice
        );

        if (item.discount > 0) {
            holder.itemDiscount.setVisibility(View.VISIBLE);
            holder.itemDiscount.setText(item.discount + "% OFF (Original: ₹" + item.price + ")");
        } else {
            holder.itemDiscount.setVisibility(View.GONE);
        }

        holder.itemCategory.setText(
                item.category
        );

        holder.itemStatus.setText(
                item.isAvailable
                        ? "Available"
                        : "Unavailable"
        );

        /* =====================================
           IMAGE LOADING (UPDATED)
        ===================================== */
        // Try to get resource ID from drawable folder using the imageUrl string
        int imageResId = activity.getResources().getIdentifier(item.imageUrl, "drawable", activity.getPackageName());

        if (imageResId != 0) {
            Glide.with(activity)
                    .load(imageResId)
                    .placeholder(R.drawable.hotel_bg)
                    .into(holder.itemImage);
        } else {
            Glide.with(activity)
                    .load(item.imageUrl)
                    .placeholder(R.drawable.hotel_bg)
                    .into(holder.itemImage);
        }

        /* =====================================
           EDIT BUTTON
        ===================================== */

        holder.editBtn.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            activity,
                            AddEditMenuItemActivity.class
                    );

            intent.putExtra(
                    "item",
                    item
            );

            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {

        return menuItems.size();
    }

    /* =========================================
       VIEW HOLDER
    ========================================= */

    static class MenuViewHolder
            extends RecyclerView.ViewHolder {

        ImageView itemImage;

        TextView itemName,
                itemPrice,
                itemDiscount,
                itemCategory,
                itemStatus;

        Button editBtn;

        public MenuViewHolder(
                @NonNull View itemView
        ) {

            super(itemView);

            itemImage =
                    itemView.findViewById(
                            R.id.adminItemImage
                    );

            itemName =
                    itemView.findViewById(
                            R.id.adminItemName
                    );

            itemPrice =
                    itemView.findViewById(
                            R.id.adminItemPrice
                    );

            itemDiscount =
                    itemView.findViewById(
                            R.id.adminItemDiscount
                    );

            itemCategory =
                    itemView.findViewById(
                            R.id.adminItemCategory
                    );

            itemStatus =
                    itemView.findViewById(
                            R.id.adminItemStatus
                    );

            editBtn =
                    itemView.findViewById(
                            R.id.editMenuItemBtn
                    );
        }
    }
}
