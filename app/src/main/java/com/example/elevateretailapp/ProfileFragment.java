package com.example.elevateretailapp;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button historyBtn = view.findViewById(R.id.btn_transaction_history);
        Button shippingAddressesBtn = view.findViewById(R.id.btn_shipping);
        Button wishlistBtn = view.findViewById(R.id.btn_wishlist);
        Button paymentMethodsBtn = view.findViewById(R.id.btn_payment_methods);
        Button reviewBtn = view.findViewById(R.id.btn_profile_review);
        RecyclerView RW_recyclerview = view.findViewById(R.id.recent_wishlist_recycler);
        RecyclerView SI_recyclerview = view.findViewById(R.id.suggested_item_recycler);

        historyBtn.setOnClickListener(v ->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.FragmentContainer, new TransactionHistoryFragment())
                        .commit()
        );

        shippingAddressesBtn.setOnClickListener(v ->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.FragmentContainer, new ShippingAddressesFragment())
                        .commit()
        );

        wishlistBtn.setOnClickListener(v ->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.FragmentContainer, new WishlistFragment())
                        .commit()
        );

        paymentMethodsBtn.setOnClickListener(v ->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.FragmentContainer, new PaymentMethodsFragment())
                        .commit()
        );

        //Mock data for recyclerviews in profile page
        ArrayList<ProfileProductItem> mockProfileItems1 = new ArrayList<>();
        mockProfileItems1.add(new ProfileProductItem(R.drawable.product_image, "Pencils", "$12.99"));
        mockProfileItems1.add(new ProfileProductItem(R.drawable.product_image, "One Piece Statue", "$59.99"));
        mockProfileItems1.add(new ProfileProductItem(R.drawable.product_image, "Notebook", "$5.99"));
        mockProfileItems1.add(new ProfileProductItem(R.drawable.product_image, "Lamp", "$55.99"));
        mockProfileItems1.add(new ProfileProductItem(R.drawable.product_image, "Shoes", "$79.99"));

        ArrayList<ProfileProductItem> mockProfileItems2 = new ArrayList<>();
        mockProfileItems2.add(new ProfileProductItem(R.drawable.product_image, "Headphones", "$99.99"));
        mockProfileItems2.add(new ProfileProductItem(R.drawable.product_image, "Notepad", "$3.99"));
        mockProfileItems2.add(new ProfileProductItem(R.drawable.product_image, "Xbox Controller", "$109.99"));
        mockProfileItems2.add(new ProfileProductItem(R.drawable.product_image, "Drawing Tablet", "$159.99"));
        mockProfileItems2.add(new ProfileProductItem(R.drawable.product_image, "Pens", "$7.99"));



        RW_RecyclerViewAdapter adapter1 = new RW_RecyclerViewAdapter(requireContext(), mockProfileItems1);
        RW_recyclerview.setAdapter(adapter1);
        RW_recyclerview.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false));

        SI_RecyclerViewAdapter adapter2 = new SI_RecyclerViewAdapter(requireContext(), mockProfileItems2);
        SI_recyclerview.setAdapter(adapter2);
        SI_recyclerview.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false));

        return view;
    }
}