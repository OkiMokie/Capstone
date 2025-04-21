package com.example.elevateretailapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link purchaseSuccessfulFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class purchaseSuccessfulFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public purchaseSuccessfulFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment purchaseSuccessfulFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static purchaseSuccessfulFragment newInstance(String param1, String param2) {
        purchaseSuccessfulFragment fragment = new purchaseSuccessfulFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_purchase_successful, container, false);
        RecyclerView successfulRecyclerView = view.findViewById(R.id.purchased_item_recycler);
        TextView totalTodayTxt = view.findViewById(R.id.total_today);

        double grandTotal = 0.0;

        List<Product> cartItems = CartManager.getCartItems();

        for (Product product : cartItems) {
            try {
                double unitPrice = Double.parseDouble(product.getPrice().replace("$", ""));
                grandTotal += unitPrice * product.getQuantity();
            } catch (NumberFormatException e) {
                // Optional Log I don't know what to put lol - FF
            }
        }

        totalTodayTxt.setText(String.format("$%.2f", grandTotal));

        PurchaseSuccessful_RecyclerViewAdapter adapter = new PurchaseSuccessful_RecyclerViewAdapter(requireContext(), cartItems);
        successfulRecyclerView.setAdapter(adapter);
        successfulRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        new Handler().postDelayed(CartManager::clearCart, 100);

        return view;
    }
}