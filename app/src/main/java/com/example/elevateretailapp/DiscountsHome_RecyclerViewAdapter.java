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

public class DiscountsHome_RecyclerViewAdapter extends RecyclerView.Adapter<DiscountsHome_RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Product> homeDiscountList;
    private OnProductClickListener listener;

    public DiscountsHome_RecyclerViewAdapter(ArrayList<Product> homeDiscountList, Context context, OnProductClickListener listener) {
        this.homeDiscountList = homeDiscountList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DiscountsHome_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_profile_product, parent, false);
        return new DiscountsHome_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountsHome_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //Change Product to Discount since it'll be from new POJO
        Product item = homeDiscountList.get(position);

        holder.discountName.setText(item.getProduct_name());
        holder.discountPrice.setText(item.getPrice());
        holder.discountImage.setImageResource(item.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onProductClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return homeDiscountList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView discountName, discountPrice;
        ImageView discountImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            discountName = itemView.findViewById(R.id.product_name);
            discountPrice = itemView.findViewById(R.id.product_price);
            discountImage = itemView.findViewById(R.id.product_image);
        }
    }

}
