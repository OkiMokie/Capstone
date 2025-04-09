package com.example.elevateretailapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Adapter used for the recyclerview in the shipping address fragment
class ShipAdd_RecyclerViewAdapter extends RecyclerView.Adapter<ShipAdd_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ShippingAddressItem> shippingAddressList;

    public ShipAdd_RecyclerViewAdapter(Context context, ArrayList<ShippingAddressItem> shippingAddressList) {
        this.context = context;
        this.shippingAddressList = shippingAddressList;
    }


    @NonNull
    @Override
    public ShipAdd_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_shipping_address,parent, false);
        return new ShipAdd_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShipAdd_RecyclerViewAdapter.MyViewHolder holder, int position) {

        String address2 = shippingAddressList.get(position).getAddress2();

        holder.nameTXT.setText(shippingAddressList.get(position).getName());
        holder.address1TXT.setText(shippingAddressList.get(position).getAddress1());
        if (address2 == null || address2.isEmpty()) {
            holder.address2TXT.setVisibility(View.GONE);
        } else {
            holder.address2TXT.setVisibility(View.VISIBLE);
            holder.address2TXT.setText(shippingAddressList.get(position).getAddress2());
        }
        holder.cityTXT.setText(shippingAddressList.get(position).getCity());
        holder.stateTXT.setText(shippingAddressList.get(position).getState());
        holder.zipTXT.setText(shippingAddressList.get(position).getZip());
        holder.countryTXT.setText(shippingAddressList.get(position).getCountry());

        holder.editBTN.setOnClickListener(v -> {
            //Button can bring to edit address fragment for that address ID
        });

        holder.removeBTN.setOnClickListener(v -> {
            //Button can remove address from fragment for that address ID
            //Add "remove/delete address" date info from ERD
        });

    }

    @Override
    public int getItemCount() {
        return shippingAddressList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameTXT, address1TXT, address2TXT, cityTXT, stateTXT, zipTXT, countryTXT;
        Button editBTN, removeBTN;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameTXT = itemView.findViewById(R.id.ship_name);
            this.address1TXT = itemView.findViewById(R.id.ship_address1);
            this.address2TXT = itemView.findViewById(R.id.ship_address2);
            this.cityTXT = itemView.findViewById(R.id.ship_city);
            this.stateTXT = itemView.findViewById(R.id.ship_state);
            this.zipTXT = itemView.findViewById(R.id.ship_zip);
            this.countryTXT = itemView.findViewById(R.id.ship_country);
            this.editBTN = itemView.findViewById(R.id.btn_edit);
            this.removeBTN = itemView.findViewById(R.id.btn_remove);
        }
    }

}
