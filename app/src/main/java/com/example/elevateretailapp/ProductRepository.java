package com.example.elevateretailapp;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public interface ProductCallback {
        void onSuccess(List<Product> productList);
        void onError(String errorMessage);
    }

    public static void getProducts(Context context, ProductCallback callback) {
        String url = APICon.Product_URL;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        List<Product> productList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);

                            String imageUrl = obj.optString("image_url", null);
                            if (imageUrl == null || imageUrl.isEmpty() || imageUrl.equals("null")) {
                                imageUrl = null;
                            }

                            Product product = new Product(
                                    obj.getInt("product_id"),
                                    obj.getString("product_name"),
                                    obj.getString("product_description"),
                                    obj.getInt("category_id"),
                                    obj.getInt("supplier_id"),
                                    R.drawable.product_image, // fallback drawable
                                    obj.getString("price"),
                                    imageUrl // remote image URL
                            );

                            productList.add(product);
                        }
                        callback.onSuccess(productList);
                    } catch (JSONException e) {
                        callback.onError("Parsing error: " + e.getMessage());
                    }
                },
                error -> callback.onError("Network error: " + error.toString())
        );

        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }
}
