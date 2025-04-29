package com.example.elevateretailapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class PurchaseSuccessful_RecyclerViewAdapter extends RecyclerView.Adapter<PurchaseSuccessful_RecyclerViewAdapter.MyViewHolder> {

    private final Context context;
    private final List<Product> purchaseSuccessfulList;

    public PurchaseSuccessful_RecyclerViewAdapter(Context context, List<Product> purchaseSuccessfulList) {
        this.context = context;
        this.purchaseSuccessfulList = purchaseSuccessfulList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order_success, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product = purchaseSuccessfulList.get(position);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM. d, yyyy");
        String today = LocalDate.now().format(formatter);

        holder.itemName.setText(product.getProduct_name());
        holder.itemDate.setText(today);
        holder.itemQuantity.setText(String.valueOf(product.getQuantity()));

        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty() && !product.getImageUrl().equalsIgnoreCase("null")) {
            Glide.with(context)
                    .load(product.getImageUrl())
                    .placeholder(product.getImageResId())
                    .into(holder.itemImage);
        } else {
            holder.itemImage.setImageResource(product.getImageResId());
        }
    }

    @Override
    public int getItemCount() {
        return purchaseSuccessfulList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemDate, itemQuantity;
        ImageView itemImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.success_item_name);
            itemDate = itemView.findViewById(R.id.success_item_date);
            itemQuantity = itemView.findViewById(R.id.success_quantity);
            itemImage = itemView.findViewById(R.id.success_product_image);
        }
    }
}
