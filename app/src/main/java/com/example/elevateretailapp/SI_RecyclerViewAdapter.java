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

//The adapter used for the suggested items recyclerview in the profile page
class SI_RecyclerViewAdapter extends RecyclerView.Adapter<SI_RecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<Product> suggestedItemsList;
    public SI_RecyclerViewAdapter(Context context, ArrayList<Product> suggestedItemsList) {
        this.context = context;
        this.suggestedItemsList = suggestedItemsList;
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

        holder.imageView.setImageResource(suggestedItemsList.get(position).getImageResId());
        holder.productName.setText(suggestedItemsList.get(position).getProduct_name());
        holder.productPrice.setText(suggestedItemsList.get(position).getPrice());
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
