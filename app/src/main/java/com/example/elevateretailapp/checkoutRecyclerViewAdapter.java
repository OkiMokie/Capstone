package com.example.elevateretailapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class checkoutRecyclerViewAdapter extends RecyclerView.Adapter<checkoutRecyclerViewAdapter.MyViewHolder> {
    @NonNull
    @Override
    public checkoutRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull checkoutRecyclerViewAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView productName, productPrice, productQuantity;
        ImageView productImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
