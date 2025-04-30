package com.example.elevateretailapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView arrivalsRecycler;
    private RecyclerView featuredRecycler;

    public HomeFragment() {}

    // Inflates the home fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home2, container, false);

        arrivalsRecycler = rootView.findViewById(R.id.new_arrivals_recycler);
        featuredRecycler = rootView.findViewById(R.id.featured_items_recycler);

        // Initial data fetch when the fragment is created
        fetchNewArrivals();
        fetchFeaturedItems();

        return rootView;
    }

    // Fetches the first 6 products from the API to display as new arrivals
    private void fetchNewArrivals() {
        APIHelper.fetchAllProducts(getContext(),
                response -> {
                    ArrayList<Product> newArrivalsList = new ArrayList<>();
                    try {
                        for (int i = 0; i < Math.min(6, response.length()); i++) {
                            JSONObject obj = response.getJSONObject(i);

                            Product product = new Product(
                                    obj.getInt("id"),
                                    obj.getString("name"),
                                    obj.getString("description"),
                                    obj.getInt("category_id"),
                                    obj.getInt("supplier_id"),
                                    R.drawable.product_image,
                                    obj.getString("unit_price"),
                                    obj.optString("image_url", null)
                            );

                            newArrivalsList.add(product);
                        }
                    } catch (JSONException e) {
                        // Log the error with a detailed message
                        Log.e("HomeFragment", "Error parsing new arrivals JSON response", e);
                    }

                    // Setting up the adapter for new arrivals
                    HomeArrivals_RecyclerViewAdapter adapter = new HomeArrivals_RecyclerViewAdapter(
                            newArrivalsList,
                            getContext(),
                            item -> {
                                if (getActivity() instanceof MainActivity) {
                                    ((MainActivity) getActivity()).setCurrentFragment(
                                            new itemPage(
                                                    item.getProduct_id(),
                                                    item.getProduct_name(),
                                                    item.getProduct_description(),
                                                    item.getCategory_id(),
                                                    item.getSupplier_id(),
                                                    item.getImageResId(),
                                                    item.getPrice(),
                                                    item.getImageUrl()
                                            )
                                    );
                                }
                            }
                    );
                    arrivalsRecycler.setAdapter(adapter);
                    arrivalsRecycler.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
                },
                error -> {
                    // Log the error with a detailed message
                    Log.e("HomeFragment", "Error fetching new arrivals", error);
                    Toast.makeText(getContext(), "Failed to load new arrivals", Toast.LENGTH_SHORT).show();
                }
        );
    }

    // Fetches products 7–18 from the API to display as featured items in a grid
    private void fetchFeaturedItems() {
        APIHelper.fetchAllProducts(getContext(),
                response -> {
                    ArrayList<Product> featuredList = new ArrayList<>();
                    try {
                        for (int i = 6; i < Math.min(18, response.length()); i++) {
                            JSONObject obj = response.getJSONObject(i);

                            Product product = new Product(
                                    obj.getInt("id"),
                                    obj.getString("name"),
                                    obj.getString("description"),
                                    obj.getInt("category_id"),
                                    obj.getInt("supplier_id"),
                                    R.drawable.product_image,
                                    obj.getString("unit_price"),
                                    obj.optString("image_url", null)
                            );

                            featuredList.add(product);
                        }
                    } catch (JSONException e) {
                        // Log the error with a detailed message
                        Log.e("HomeFragment", "Error parsing featured items JSON response", e);
                    }

                    // Setting up the adapter for featured items
                    HomeFeatured_RecyclerViewAdapter adapter = new HomeFeatured_RecyclerViewAdapter(
                            featuredList,
                            getContext(),
                            item -> {
                                if (getActivity() instanceof MainActivity) {
                                    ((MainActivity) getActivity()).setCurrentFragment(
                                            new itemPage(
                                                    item.getProduct_id(),
                                                    item.getProduct_name(),
                                                    item.getProduct_description(),
                                                    item.getCategory_id(),
                                                    item.getSupplier_id(),
                                                    item.getImageResId(),
                                                    item.getPrice(),
                                                    item.getImageUrl()
                                            )
                                    );
                                }
                            }
                    );
                    featuredRecycler.setAdapter(adapter);
                    featuredRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 3));
                },
                error -> {
                    // Log the error with a detailed message
                    Log.e("HomeFragment", "Error fetching featured items", error);
                    Toast.makeText(getContext(), "Failed to load featured items", Toast.LENGTH_SHORT).show();
                }
        );
    }

    // Re-fetch data every time the fragment becomes visible again
    @Override
    public void onResume() {
        super.onResume();
        fetchNewArrivals();
        fetchFeaturedItems();
    }
}
