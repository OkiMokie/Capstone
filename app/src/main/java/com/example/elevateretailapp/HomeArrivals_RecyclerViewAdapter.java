package com.example.elevateretailapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

//Adapter used for new arrivals recycler view in home fragment
public class HomeArrivals_RecyclerViewAdapter extends RecyclerView.Adapter<HomeArrivals_RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Product> homeProductItemList;
    private OnProductClickListener listener;

    public HomeArrivals_RecyclerViewAdapter(ArrayList<Product> homeProductItemList, Context context, OnProductClickListener listener) {
        this.homeProductItemList = homeProductItemList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeArrivals_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_profile_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeArrivals_RecyclerViewAdapter.MyViewHolder holder, int position) {
        Product item = homeProductItemList.get(position);

        holder.productName.setText(item.getProduct_name());
        holder.productPrice.setText(item.getPrice());

        if (item.getImageUrl() != null && !item.getImageUrl().isEmpty() && !item.getImageUrl().equalsIgnoreCase("null")) {
            Glide.with(context)
                    .load(item.getImageUrl())
                    .placeholder(item.getImageResId())
                    .into(holder.productImage);
        } else {
            holder.productImage.setImageResource(item.getImageResId());
        }

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
