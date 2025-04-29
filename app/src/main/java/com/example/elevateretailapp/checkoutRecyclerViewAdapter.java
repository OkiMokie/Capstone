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

import java.util.List;

public class checkoutRecyclerViewAdapter extends RecyclerView.Adapter<checkoutRecyclerViewAdapter.MyViewHolder> {

    private List<Product> productList;
    private Context context;

    public checkoutRecyclerViewAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public checkoutRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checkout_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull checkoutRecyclerViewAdapter.MyViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.productName.setText(product.getProduct_name());
        holder.productQuantity.setText(String.valueOf(product.getQuantity()));
        holder.productPrice.setText(product.getPrice());

        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty() && !product.getImageUrl().equalsIgnoreCase("null")) {
            Glide.with(context)
                    .load(product.getImageUrl())
                    .placeholder(product.getImageResId())
                    .into(holder.productImage);
        } else {
            holder.productImage.setImageResource(product.getImageResId());
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productQuantity, productPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.checkout_product_image);
            productName = itemView.findViewById(R.id.checkout_product_name);
            productQuantity = itemView.findViewById(R.id.checkout_quantity_txt);
            productPrice = itemView.findViewById(R.id.checkout_product_price);
        }
    }
}
