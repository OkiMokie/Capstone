package com.example.elevateretailapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class APIHelper {

    public static void fetchAllProducts(Context context, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        String url = APICon.Product_URL;
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);
        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }
}
