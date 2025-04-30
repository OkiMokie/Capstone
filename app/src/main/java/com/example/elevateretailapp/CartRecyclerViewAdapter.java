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

//Adapter class used for RecyclerView in cart fragment
public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder> {

    private List<Product> cartItemList;
    private Context context;
    private final OnCartChangedListener cartChangedListener;

    public CartRecyclerViewAdapter(List<Product> cartItemList, Context context, OnCartChangedListener listener) {
        this.cartItemList = cartItemList;
        this.context = context;
        this.cartChangedListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = cartItemList.get(position);

        holder.quantityText.setText(String.valueOf(product.getQuantity()));
        holder.productName.setText(product.getProduct_name());
        holder.productPrice.setText(product.getPrice());

        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty() && !product.getImageUrl().equalsIgnoreCase("null")) {
            Glide.with(context)
                    .load(product.getImageUrl())
                    .placeholder(product.getImageResId())
                    .into(holder.productImage);
        } else {
            holder.productImage.setImageResource(product.getImageResId());
        }

        holder.plusButton.setOnClickListener(v -> {
            product.setQuantity(product.getQuantity() + 1);
            notifyItemChanged(position);

            if (cartChangedListener != null) {
                cartChangedListener.onCartChanged();
            }
        });

        holder.minusButton.setOnClickListener(v -> {
            int currentQty = product.getQuantity();
            if (currentQty > 1) {
                product.setQuantity(currentQty - 1);
                notifyItemChanged(position);
            } else {
                cartItemList.remove(position);
                CartManager.removeFromCart(product);
                notifyItemRemoved(position);
            }

            if (cartChangedListener != null) {
                cartChangedListener.onCartChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, quantityText;
        ImageView productImage, plusButton, minusButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.cart_product_name);
            productPrice = itemView.findViewById(R.id.cart_product_price);
            productImage = itemView.findViewById(R.id.cart_product_image);
            plusButton = itemView.findViewById(R.id.plus_btn);
            minusButton = itemView.findViewById(R.id.minus_btn);
            quantityText = itemView.findViewById(R.id.quantity_txt);
        }
    }
}
