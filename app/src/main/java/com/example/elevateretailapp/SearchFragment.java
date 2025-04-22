package com.example.elevateretailapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment{

    private String searchQuery;

    public SearchFragment(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public SearchFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_page, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.search_recycler);

        // 🔥 TRIGGER API CALL AND HANDLE IT HERE
        ProductRepository.getProducts(requireContext(), new ProductRepository.ProductCallback() {
            @Override
            public void onSuccess(List<Product> productList) {
                List<Product> filteredList = new ArrayList<>();
                for (Product p : productList) {
                    if (p.getProduct_name().toLowerCase().contains(searchQuery.toLowerCase())) {
                        filteredList.add(p);
                    }
                }

                Search_RecyclerViewAdapter adapter = new Search_RecyclerViewAdapter(filteredList, getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("SearchFragment", "Fetch error: " + errorMessage);
            }
        });

        return view;
    }

}
