package com.example.elevateretailapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//The adapter used for the Wishlist recyclerview in the Wishlist fragment
public class PurchaseSuccessful_RecyclerViewAdapter extends RecyclerView.Adapter<PurchaseSuccessful_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<WishlistItem> purchaseSuccessfulList;

    public PurchaseSuccessful_RecyclerViewAdapter(Context context, ArrayList<WishlistItem> wishList) {
        this.context = context;
        this.purchaseSuccessfulList = wishList;
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
        holder.itemName.setText(purchaseSuccessfulList.get(position).getItemName());
        holder.itemDate.setText(purchaseSuccessfulList.get(position).getItemDate());
        holder.itemImage.setImageResource(purchaseSuccessfulList.get(position).getItemImage());
    }

    @Override
    public int getItemCount() {
        return purchaseSuccessfulList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemDate;
        ImageView itemImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.success_item_name);
            itemDate = itemView.findViewById(R.id.success_item_date);
            itemImage = itemView.findViewById(R.id.success_product_image);



        }
    }
}
