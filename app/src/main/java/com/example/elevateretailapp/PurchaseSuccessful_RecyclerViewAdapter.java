package com.example.elevateretailapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//The adapter used for the Wishlist recyclerview in the Wishlist fragment
public class PurchaseSuccessful_RecyclerViewAdapter extends RecyclerView.Adapter<PurchaseSuccessful_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    List<Product> purchaseSuccessfulList;

    public PurchaseSuccessful_RecyclerViewAdapter(Context context, List<Product> purchaseSuccessfulList) {
        this.context = context;
        this.purchaseSuccessfulList = purchaseSuccessfulList;
    }

    @NonNull
    @Override
    public PurchaseSuccessful_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_order_success, parent, false);
        return new PurchaseSuccessful_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseSuccessful_RecyclerViewAdapter.MyViewHolder holder, int position) {
        Product product = purchaseSuccessfulList.get(position);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM., d, yyyy");
        String today = LocalDate.now().format(formatter);

        holder.itemName.setText(product.getProduct_name());
        holder.itemDate.setText(today);
        holder.itemImage.setImageResource(purchaseSuccessfulList.get(position).getImageResId());
        holder.itemQuantity.setText(String.valueOf(product.getQuantity()));
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
