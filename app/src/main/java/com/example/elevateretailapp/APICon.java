package com.example.elevateretailapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class APICon {
    // endpoints
    public static final String API_URL = "http://137.184.40.118:5000";
    public static final String Shipping_URL = API_URL + "/shipping";
    public static final String Product_URL = API_URL + "/product";
    public static final String Customer_URL = API_URL + "/customer";

    public static final String Customer_Address_URL = API_URL + "/customer_address";
    public static final String Discount_URL = API_URL + "/discount";
    public static final String Inventory_URL = API_URL + "/inventory";
    public static final String Member_URL = API_URL + "/member";
    public static final String Order_URL = API_URL + "/order";
    public static final String Order_Item_URL = API_URL + "/order_item";
    public static final String Payment_URL = API_URL + "/payment";
    public static final String product_category_URL = API_URL + "/product_category";
    public static final String purchase_order_URL = API_URL + "/purchase_order";
    public static final String Shopping_Cart_URL = API_URL + "/shopping_cart";
    public static final String Shopping_Cart_Item_URL = API_URL + "/shopping_cart_item";
    public static final String Supplier_URL = API_URL + "/supplier";




}
