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


//The Wishlist fragment that displays the customers wishlist
public class WishlistFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wishlist, container, false);
        Button backButton = view.findViewById(R.id.back_to_profile_btn);
        RecyclerView recyclerView = view.findViewById(R.id.wishlist_recycler);

        backButton.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.FragmentContainer, new ProfileFragment())
                    .commit();

        });

        ArrayList<WishlistItem> mockWishList = new ArrayList<>();
        mockWishList.add(new WishlistItem("Coke Machine", "Jan. 1, 2025", R.drawable.product_image));

        PurchaseSuccessful_RecyclerViewAdapter adapter = new PurchaseSuccessful_RecyclerViewAdapter(requireContext(), mockWishList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        return view;
    };

}