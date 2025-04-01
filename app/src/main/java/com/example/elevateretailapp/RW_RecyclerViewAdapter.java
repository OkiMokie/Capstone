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

//Adapter class to attach ProfileProductItem to MockListArray1 using Product item layout
//For the first recyclerview in profile fragment
class RW_RecyclerViewAdapter extends RecyclerView.Adapter<RW_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProfileProductItem> recentlyWishlistedList;
    public RW_RecyclerViewAdapter(Context context, ArrayList<ProfileProductItem> recentlyWishlistedList) {
        this.context = context;
        this.recentlyWishlistedList = recentlyWishlistedList;
    }

    @NonNull
    @Override
    public RW_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_profile_product,parent, false);
        return new RW_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RW_RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.imageView.setImageResource(recentlyWishlistedList.get(position).getProductImage());
        holder.productName.setText(recentlyWishlistedList.get(position).getProductName());
        holder.productPrice.setText(recentlyWishlistedList.get(position).getProductPrice());
    }

    @Override
    public int getItemCount() {
        return recentlyWishlistedList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView productName, productPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);

        }
    }
}
