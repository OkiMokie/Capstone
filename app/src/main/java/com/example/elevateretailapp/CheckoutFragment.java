package com.example.elevateretailapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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

    //Used for voucher code redemption
    boolean voucherIsValid = true;

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

        // ref the widgets
        RecyclerView checkoutRecycler = view.findViewById(R.id.itemsRecyclerView);
        TextView grandTotalTextView = view.findViewById(R.id.checkout_total_amount);
        EditText voucher = view.findViewById(R.id.voucher_edit_text);
        Button redeemBtn = view.findViewById(R.id.redeemBtn);
        Button placeOrderBtn = view.findViewById(R.id.placeYourOrderBtn);


        double tempTotal = 0.0;

        List<Product> cartItems = CartManager.getCartItems();

        // the adapter to be used for checkoutRecycler recyclerview
        checkoutRecyclerViewAdapter adapter = new checkoutRecyclerViewAdapter(cartItems, requireContext());
        checkoutRecycler.setAdapter(adapter);
        checkoutRecycler.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));




        // calculates and gives the grand total of things in cart, displays in a text view
        for (Product product : cartItems) {
            try {
                double unitPrice = Double.parseDouble(product.getPrice().replace("$", ""));
                tempTotal += unitPrice * product.getQuantity() + (unitPrice * product.getQuantity() * .07);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        final double grandTotal = tempTotal; 

        grandTotalTextView.setText(String.format("Total w/ Tax: $%.2f", grandTotal));

        //Watches if text changes when entering voucher
        voucher.addTextChangedListener(new TextWatcher() {
            //Not a used method, but implemented just in case
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String code = s.toString().trim();
                double tempTotal = 0.0;
                if (code.isEmpty()) {
                    voucherIsValid = true;  // If nothing is typed, allow them
                    for (Product product : cartItems) {
                        try {
                            double unitPrice = Double.parseDouble(product.getPrice().replace("$", ""));
                            tempTotal += unitPrice * product.getQuantity() + (unitPrice * product.getQuantity() * 0.07);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }

                    grandTotalTextView.setText(String.format("Total w/ Tax: $%.2f", grandTotal));

                } else {
                    voucherIsValid = false; // If they type anything that isn't the code, makes false
                }
            }

            //Not a used method, but implemented just in case
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //If enter is pressed while editing voucher box, simulates pressing redeem button
        voucher.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {

                    redeemBtn.performClick();  // Simulate redeem button click
                    return true;
                }
                return false;
            }
        });

        redeemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = voucher.getText().toString().trim();
                double tempTotal = 0.0;

                if (code.isEmpty()) {
                    voucherIsValid = true;
                    Toast.makeText(requireContext(), "No code entered. Proceeding with full price", Toast.LENGTH_SHORT).show();
                } else if (code.equals("Bucky")) {
                    voucherIsValid = true;
                    Toast.makeText(requireContext(), "Voucher accepted!", Toast.LENGTH_SHORT).show();
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

                    grandTotalTextView.setText(String.format("Total w/ Tax: $%.2f", tempTotal));

                    voucher.setEnabled(false);     // Lock the EditText
                    redeemBtn.setEnabled(false);     // Grey out the redeem button
                }
                else {
                    voucherIsValid = false;
                    Toast.makeText(requireContext(), "Invalid code.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Button listener
        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tempTotal = 0.0;

                if (voucherIsValid) {

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
                else {
                    Toast.makeText(requireContext(), "Invalid code. Please remove or enter a valid code.", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;
    }


}