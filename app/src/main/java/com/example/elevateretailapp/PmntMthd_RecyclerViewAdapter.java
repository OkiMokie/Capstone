package com.example.elevateretailapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//The adapter that is used for the array in payment methods fragment and the recyclerview on that layout
public class PmntMthd_RecyclerViewAdapter extends RecyclerView.Adapter<PmntMthd_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<PaymentMethodItem> paymentMethodList;

    public PmntMthd_RecyclerViewAdapter(Context context, ArrayList<PaymentMethodItem> paymentMethodList) {
        this.context = context;
        this.paymentMethodList = paymentMethodList;
    }

    @NonNull
    @Override
    public PmntMthd_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_payment_method, parent, false);
        return new PmntMthd_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PmntMthd_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.cardNum.setText(paymentMethodList.get(position).getCardNum());
        holder.cardLogo.setImageResource(paymentMethodList.get(position).getCardTypeImage());
    }

    @Override
    public int getItemCount() {
        return paymentMethodList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cardNum;
        ImageView cardLogo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardNum = itemView.findViewById(R.id.card_number);
            cardLogo = itemView.findViewById(R.id.card_logo_image);


        }
    }
}
