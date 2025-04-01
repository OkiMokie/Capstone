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
//Inflates the fragment/screen for transaction history to show previously purchased items
public class TransactionHistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflates
        View view = inflater.inflate(R.layout.fragment_transaction_history, container, false);
        //Button and recyclerview assigned to variable
        Button backButton = view.findViewById(R.id.back_to_profile_btn);
        RecyclerView recyclerView = view.findViewById(R.id.transaction_recycler);

        //Listener for back button
        backButton.setOnClickListener(v -> {

            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.FragmentContainer, new ProfileFragment())
                    .commit();

        });

        //Mock data to show transaction history in fragment
        ArrayList<OrderedTransactionItem> tempList = new ArrayList<>();
        tempList.add(new OrderedTransactionItem("Socks", "Jan 26th, 2025"));
        tempList.add(new OrderedTransactionItem("Phone Case", "Feb. 8th, 2025"));
        tempList.add(new OrderedTransactionItem("Key Chain", "Mar. 1st, 2025"));

        //Adapter code
        OT_RecyclerViewAdapter adapter = new OT_RecyclerViewAdapter(requireContext(), tempList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    };

}
