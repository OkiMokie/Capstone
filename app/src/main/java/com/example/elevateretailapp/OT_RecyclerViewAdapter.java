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

//Adapter class to attach OrderTransactionItem to array using item layout
public class OT_RecyclerViewAdapter extends RecyclerView.Adapter<OT_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<OrderedTransactionItem> tempList;

    public OT_RecyclerViewAdapter(Context context, ArrayList<OrderedTransactionItem> tempList)  {
        this.context = context;
        this.tempList = tempList;
    }

    @NonNull
    @Override
    public OT_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_transaction_history, parent, false);
        return new OT_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OT_RecyclerViewAdapter.MyViewHolder holder, int position) {
        OrderedTransactionItem item = tempList.get(position);

        holder.tvOrder.setText(tempList.get(position).getOrderedItem());
        holder.tvDate.setText(tempList.get(position).getOrderedItemDate());
        holder.tvStatus.setText(tempList.get(position).getOrderedItemStatus());

        Glide.with(holder.imageView.getContext())
                .load(item.getOrderedImage())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return tempList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvOrder, tvDate, tvStatus;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image);
            tvOrder = itemView.findViewById(R.id.ordered_item_name);
            tvDate = itemView.findViewById(R.id.ordered_item_date);
            tvStatus = itemView.findViewById(R.id.ordered_item_status);


        }
    }
};
