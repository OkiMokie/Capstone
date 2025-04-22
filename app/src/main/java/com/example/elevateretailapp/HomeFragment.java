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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView arrivalsRecycler;
    private RecyclerView featuredRecycler;
    private ShimmerFrameLayout shimmerContainer;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home2, container, false);

        arrivalsRecycler = rootView.findViewById(R.id.new_arrivals_recycler);
        featuredRecycler = rootView.findViewById(R.id.featured_items_recycler);

        fetchNewArrivals();
        fetchFeaturedItems();

        return rootView;
    }

    private void fetchNewArrivals() {
        APIHelper.fetchAllProducts(getContext(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Product> newArrivalsList = new ArrayList<>();
                try {
                    for (int i = 0; i < Math.min(6, response.length()); i++) {
                        JSONObject obj = response.getJSONObject(i);

                        int product_id = obj.getInt("id");
                        String name = obj.getString("name");
                        String description = obj.getString("description");
                        int category_id = obj.getInt("category_id");
                        int supplier_id = obj.getInt("supplier_id");
                        String price = obj.getString("unit_price");

                        newArrivalsList.add(new Product(product_id, name, description, category_id, supplier_id, R.drawable.product_image, price));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                HomeArrivals_RecyclerViewAdapter adapter = new HomeArrivals_RecyclerViewAdapter(
                        newArrivalsList,
                        getContext(),
                        new OnProductClickListener() {
                            @Override
                            public void onProductClick(Product item) {
                                if (getActivity() instanceof MainActivity) {
                                    ((MainActivity) getActivity()).setCurrentFragment(
                                            new itemPage(
                                                    item.getProduct_id(),
                                                    item.getProduct_name(),
                                                    item.getProduct_description(),
                                                    item.getCategory_id(),
                                                    item.getSupplier_id(),
                                                    item.getImageResId(),
                                                    item.getPrice()
                                            )
                                    );

                                }
                            }
                        }
                );
                arrivalsRecycler.setAdapter(adapter);
                arrivalsRecycler.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Failed to load new arrivals", Toast.LENGTH_SHORT).show();
                Log.e("VOLLEY_ERR", "onErrorResponse: " + error.toString());
            }
        });
    }

    private void fetchFeaturedItems() {
        APIHelper.fetchAllProducts(getContext(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Product> featuredList = new ArrayList<>();
                try {
                    for (int i = 6; i < Math.min(18, response.length()); i++) {
                        JSONObject obj = response.getJSONObject(i);

                        int product_id = obj.getInt("id");
                        String name = obj.getString("name");
                        String description = obj.getString("description");
                        int category_id = obj.getInt("category_id");
                        int supplier_id = obj.getInt("supplier_id");
                        String price = obj.getString("unit_price");

                        featuredList.add(new Product(product_id, name, description, category_id, supplier_id, R.drawable.product_image, price));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                HomeFeatured_RecyclerViewAdapter adapter = new HomeFeatured_RecyclerViewAdapter(
                        featuredList,
                        getContext(),
                        new OnProductClickListener() {
                            @Override
                            public void onProductClick(Product item) {
                                if (getActivity() instanceof MainActivity) {
                                    ((MainActivity) getActivity()).setCurrentFragment(
                                            new itemPage(
                                                    item.getProduct_id(),
                                                    item.getProduct_name(),
                                                    item.getProduct_description(),
                                                    item.getCategory_id(),
                                                    item.getSupplier_id(),
                                                    item.getImageResId(),
                                                    item.getPrice()
                                            )
                                    );

                                }
                            }
                        }
                );
                featuredRecycler.setAdapter(adapter);
                featuredRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 3));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Failed to load featured items", Toast.LENGTH_SHORT).show();
            }
        });
    }
}