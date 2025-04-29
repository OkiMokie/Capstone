package com.example.elevateretailapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Adapter class to attach ProfileProductItem to MockListArray1 using Product item layout
//For the first recyclerview in profile fragment
class RW_RecyclerViewAdapter extends RecyclerView.Adapter<RW_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Product> recentlyWishlistedList;
    private OnProductClickListener listener;
    public RW_RecyclerViewAdapter(Context context, ArrayList<Product> recentlyWishlistedList, OnProductClickListener listener) {
        this.context = context;
        this.recentlyWishlistedList = recentlyWishlistedList;
        this.listener = listener;
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
        Product product = recentlyWishlistedList.get(position);

        holder.productName.setText(product.getProduct_name());
        holder.productPrice.setText(product.getPrice());

        // Use Glide to load image (remote or local)
        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            Glide.with(holder.imageView.getContext())
                    .load(product.getImageUrl()) // Load from URL
                    .override(500, 500)          // Optional size
                    .into(holder.imageView);
        } else {
            Glide.with(holder.imageView.getContext())
                    .load(product.getImageResId()) // Load from local drawable
                    .override(500, 500)
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick(product);
            }
        });
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
