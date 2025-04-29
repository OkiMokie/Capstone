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

//The adapter used for the suggested items recyclerview in the profile page
class SI_RecyclerViewAdapter extends RecyclerView.Adapter<SI_RecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<Product> suggestedItemsList;
    private OnProductClickListener listener;
    public SI_RecyclerViewAdapter(Context context, ArrayList<Product> suggestedItemsList, OnProductClickListener listener) {
        this.context = context;
        this.suggestedItemsList = suggestedItemsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SI_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_profile_product,parent, false);
        return new SI_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SI_RecyclerViewAdapter.MyViewHolder holder, int position) {
        Product product = suggestedItemsList.get(position);

        holder.productName.setText(product.getProduct_name());
        holder.productPrice.setText(product.getPrice());

        // Use Glide for image loading
        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            Glide.with(holder.imageView.getContext())
                    .load(product.getImageUrl())
                    .override(500, 500)
                    .into(holder.imageView);
        } else {
            Glide.with(holder.imageView.getContext())
                    .load(product.getImageResId())
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
        return suggestedItemsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

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
