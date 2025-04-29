package com.example.elevateretailapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Adapter used for the recycler view in search fragment
public class Search_RecyclerViewAdapter extends RecyclerView.Adapter<Search_RecyclerViewAdapter.MyViewHolder> {

    List<Product> searchList;
    Context context;

    public Search_RecyclerViewAdapter(List<Product> searchList, Context context) {
        this.searchList = searchList;
        this.context = context;
    }

    @NonNull
    @Override
    public Search_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_searched_product,parent, false);
        return new Search_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Search_RecyclerViewAdapter.MyViewHolder holder, int position) {
        Product product = searchList.get(position);

        holder.searchName.setText(searchList.get(position).getProduct_name());
        holder.searchDescription.setText(searchList.get(position).getProduct_description());
        holder.searchPrice.setText(searchList.get(position).getPrice());
        holder.searchImage.setImageResource(searchList.get(position).getImageResId());
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView searchImage;
        TextView searchName, searchDescription, searchPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            searchName = itemView.findViewById(R.id.search_name);
            searchDescription = itemView.findViewById(R.id.search_description);
            searchPrice = itemView.findViewById(R.id.search_price);
            searchImage = itemView.findViewById(R.id.search_image);


        }
    }
}
