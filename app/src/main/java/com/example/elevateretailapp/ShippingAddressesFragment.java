package com.example.elevateretailapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//The fragment that displays after clicking the shipping addresses button on the profile fragment
public class ShippingAddressesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shipping_addresses, container, false);
        Button backButton = view.findViewById(R.id.back_to_profile_btn);
        RecyclerView recyclerView = view.findViewById(R.id.ship_address_recycler);

        backButton.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.FragmentContainer, new ProfileFragment())
                    .commit();

        });

        ArrayList<ShippingAddressItem> mockAddressList = new ArrayList<>();
        mockAddressList.add(new ShippingAddressItem("John Doe", "123 Hazelnut Dr.", "New York", "NY", "12345", "US"));
        mockAddressList.add(new ShippingAddressItem("Jane Doe", "123 Walnut Dr.", "Apt. 1","Charlotte", "NC", "12345", "US"));

        ShipAdd_RecyclerViewAdapter adapter = new ShipAdd_RecyclerViewAdapter(requireContext(), mockAddressList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        return view;
    };

}
