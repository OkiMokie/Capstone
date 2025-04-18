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

public class HomeArrivals_RecyclerViewAdapter extends RecyclerView.Adapter<HomeArrivals_RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ProfileProductItem> homeProductItemList;
    private OnProductClickListener listener;

    public HomeArrivals_RecyclerViewAdapter(ArrayList<ProfileProductItem> homeProductItemList, Context context, OnProductClickListener listener) {
        this.homeProductItemList = homeProductItemList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeArrivals_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_profile_product, parent, false);
        return new HomeArrivals_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeArrivals_RecyclerViewAdapter.MyViewHolder holder, int position) {
        ProfileProductItem item = homeProductItemList.get(position);

        holder.productName.setText(item.getProductName());
        holder.productPrice.setText(item.getProductPrice());
        holder.productImage.setImageResource(item.getProductImage());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeProductItemList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productPrice;
        ImageView productImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}
