package com.example.elevateretailapp;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

public class APIHelper {

    public static void fetchJSONArray(Context context, String url,
                                      Response.Listener<org.json.JSONArray> listener,
                                      Response.ErrorListener errorListener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener);
        VolleySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }

    // Optional: add fetchJSONObject() or fetchString() here if you need those later
}
