package com.example.elevateretailapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckoutFragment newInstance(String param1, String param2) {
        CheckoutFragment fragment = new CheckoutFragment();
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
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        RecyclerView checkoutRecycler = view.findViewById(R.id.itemsRecyclerView);
        TextView grandTotalTextView = view.findViewById(R.id.checkout_total_amount);
        EditText voucher = view.findViewById(R.id.voucher_edit_text);

        double tempTotal = 0.0;

        List<Product> cartItems = CartManager.getCartItems();

        // the adapter to be used for checkoutRecycler recyclerview
        checkoutRecyclerViewAdapter adapter = new checkoutRecyclerViewAdapter(cartItems, requireContext());
        checkoutRecycler.setAdapter(adapter);
        checkoutRecycler.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        // ref the button
        Button placeOrderBtn = view.findViewById(R.id.placeYourOrderBtn);

        // calculates and gives the grand total of things in cart, displays in a text view
        for (Product product : cartItems) {
            try {
                double unitPrice = Double.parseDouble(product.getPrice().replace("$", ""));
                tempTotal += unitPrice * product.getQuantity() + (unitPrice * product.getQuantity() * .07);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        final double grandTotal = tempTotal; // ✅ now it's final and usable in the listener

        grandTotalTextView.setText(String.format("Total w/ Tax: $%.2f", grandTotal));

        // button listener
        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tempTotal = 0.0;

                for (Product product : cartItems) {
                    try {
                        double unitPrice = Double.parseDouble(product.getPrice().replace("$", ""));
                        tempTotal += unitPrice * product.getQuantity() + (unitPrice * product.getQuantity() * 0.07);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                String voucherCode = voucher.getText().toString().trim();
                if (voucherCode.equalsIgnoreCase("Bucky")) {
                    tempTotal -= (tempTotal * 0.25);
                }

                purchaseSuccessfulFragment fragment = purchaseSuccessfulFragment.newInstance(tempTotal);
                ((MainActivity) getActivity()).setCurrentFragment(fragment);
            }
        });
        return view;
    }


}