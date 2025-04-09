package com.example.elevateretailapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//The adapter used for the Wishlist recyclerview in the Wishlist fragment
public class WishList_RecycleViewAdapter extends RecyclerView.Adapter<WishList_RecycleViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<WishlistItem> wishList;

    public WishList_RecycleViewAdapter(Context context, ArrayList<WishlistItem> wishList) {
        this.context = context;
        this.wishList = wishList;
    }

    @NonNull
    @Override
    public WishList_RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_wishlist_product, parent, false);
        return new WishList_RecycleViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishList_RecycleViewAdapter.MyViewHolder holder, int position) {
        holder.itemName.setText(wishList.get(position).getItemName());
        holder.itemDate.setText(wishList.get(position).getItemDate());
        holder.itemImage.setImageResource(wishList.get(position).getItemImage());
    }

    @Override
    public int getItemCount() {
        return wishList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemDate;
        ImageView itemImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.wishlist_item_name);
            itemDate = itemView.findViewById(R.id.wishlist_item_date);
            itemImage = itemView.findViewById(R.id.wishlist_product_image);



        }
    }
}
