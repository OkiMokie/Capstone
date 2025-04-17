package com.example.elevateretailapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Fragment/page that displays the payment methods screen
public class PaymentMethodsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_payment_methods, container, false);
        Button backButton = view.findViewById(R.id.back_to_profile_btn);
        RecyclerView recyclerView = view.findViewById(R.id.payment_method_recycler);

        backButton.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.FragmentContainer, new ProfileFragment())
                    .commit();

        });

        ArrayList<PaymentMethodItem> mockMethodList = new ArrayList<>();
        mockMethodList.add(new PaymentMethodItem("1234", R.drawable.card_logo));

        PmntMthd_RecyclerViewAdapter adapter = new PmntMthd_RecyclerViewAdapter(requireContext(), mockMethodList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    };

}
