package com.example.elevateretailapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // ref the button
        Button checkoutButton = view.findViewById(R.id.btnCheckout);
        RecyclerView cartRecycler = view.findViewById(R.id.cartRecyclerView);
        TextView grandTotalTextView = view.findViewById(R.id.totalTextBox);
        List<Product> cartItems = CartManager.getCartItems();

        updateGrandTotal(grandTotalTextView);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).setCurrentFragment(new CheckoutFragment());
                }
            }
        });



        CartRecyclerViewAdapter adapter = new CartRecyclerViewAdapter(cartItems, getContext(), new OnCartChangedListener() {
            @Override
            public void onCartChanged() {
                updateGrandTotal(grandTotalTextView);
            }
        });
        cartRecycler.setAdapter(adapter);
        cartRecycler.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false));

        return view;
    }

    // functions

    // calcs total then places it into the total after parsing to string
    public static void updateGrandTotal(TextView grandTotalTextView) {
        double total = 0.0;
        for (Product p : CartManager.getCartItems()) {
            try {
                double price = Double.parseDouble(p.getPrice().replace("$", ""));
                total += price * p.getQuantity();
            } catch (NumberFormatException e) {
                // idk what to put for e here
            }
        }
        grandTotalTextView.setText(String.format("Total: $%.2f", total));
    }


}