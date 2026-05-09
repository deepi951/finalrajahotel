package com.example.rajahotel;

import android.app.Activity;
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

        // Calculate final price after discount
        int finalPrice = item.price;
        if (item.discount > 0) {
            finalPrice = item.price - (item.price * item.discount / 100);
        }

        holder.itemPrice.setText(
                "Rs. " + finalPrice
        );

        if (item.discount > 0) {
            holder.itemDiscount.setVisibility(View.VISIBLE);
            holder.itemDiscount.setText(item.discount + "% OFF (Original: Rs. " + item.price + ")");
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
           IMAGE LOADING
        ===================================== */

        Glide.with(activity)

                .load(item.imageUrl)

                .into(holder.itemImage);

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
