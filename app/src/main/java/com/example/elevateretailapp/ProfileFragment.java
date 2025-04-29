package com.example.elevateretailapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {}

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button historyBtn = view.findViewById(R.id.btn_transaction_history);
        Button shippingAddressesBtn = view.findViewById(R.id.btn_shipping);
        Button wishlistBtn = view.findViewById(R.id.btn_wishlist);
        Button paymentMethodsBtn = view.findViewById(R.id.btn_payment_methods);
        Button reviewBtn = view.findViewById(R.id.btn_profile_review);
        RecyclerView RW_recyclerview = view.findViewById(R.id.recent_wishlist_recycler);
        RecyclerView SI_recyclerview = view.findViewById(R.id.suggested_item_recycler);

        historyBtn.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FragmentContainer, new TransactionHistoryFragment()).commit());

        shippingAddressesBtn.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FragmentContainer, new ShippingAddressesFragment()).commit());

        wishlistBtn.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FragmentContainer, new WishlistFragment()).commit());

        paymentMethodsBtn.setOnClickListener(v ->
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.FragmentContainer, new PaymentMethodsFragment()).commit());

        ArrayList<Product> mockProfileItems1 = new ArrayList<>();
        mockProfileItems1.add(new Product(1, "Pencils", "Box of 12 graphite pencils", 101, 201, R.drawable.product_image, "$12.99", null));
        mockProfileItems1.add(new Product(2, "One Piece Statue", "Detailed anime figure", 102, 202, R.drawable.product_image, "$59.99", null));
        mockProfileItems1.add(new Product(3, "Notebook", "100 pages spiral notebook", 103, 203, R.drawable.product_image, "$5.99", null));
        mockProfileItems1.add(new Product(4, "Lamp", "Desk lamp with LED bulb", 104, 204, R.drawable.product_image, "$55.99", null));
        mockProfileItems1.add(new Product(5, "The One and Only, Bucky Barnes", "The best marvel character", 105, 205, R.drawable.product_image, "$2999.99", null));

        ArrayList<Product> mockProfileItems2 = new ArrayList<>();
        mockProfileItems2.add(new Product(6, "Headphones", "Wireless over-ear headphones", 106, 206, R.drawable.product_image, "$99.99", null));
        mockProfileItems2.add(new Product(7, "Notepad", "Sticky notepad", 107, 207, R.drawable.product_image, "$3.99", null));
        mockProfileItems2.add(new Product(8, "Xbox Controller", "Wireless gaming controller", 108, 208, R.drawable.product_image, "$109.99", null));
        mockProfileItems2.add(new Product(9, "Drawing Tablet", "Digital drawing tablet with pen", 109, 209, R.drawable.product_image, "$159.99", null));
        mockProfileItems2.add(new Product(10, "Pens", "Pack of 5 gel pens", 110, 210, R.drawable.product_image, "$7.99", null));

        RW_RecyclerViewAdapter adapter1 = new RW_RecyclerViewAdapter(
                requireContext(),
                mockProfileItems1,
                item -> {
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).setCurrentFragment(
                                new itemPage(item.getProduct_id(), item.getProduct_name(),
                                        item.getProduct_description(), item.getCategory_id(),
                                        item.getSupplier_id(), item.getImageResId(),
                                        item.getPrice(), item.getImageUrl())
                        );
                    }
                }
        );

        RW_recyclerview.setAdapter(adapter1);
        RW_recyclerview.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        SI_RecyclerViewAdapter adapter2 = new SI_RecyclerViewAdapter(requireContext(), mockProfileItems2);
        SI_recyclerview.setAdapter(adapter2);
        SI_recyclerview.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }
}
