package com.example.elevateretailapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home2, container, false);

        // ======= Product 1 =======
        View container1 = rootView.findViewById(R.id.include_new_1);
        ImageView productImage1 = container1.findViewById(R.id.product_image);
        TextView productName1 = container1.findViewById(R.id.product_name);
        TextView productPrice1 = container1.findViewById(R.id.product_price);

        productName1.setText("Sneakers");
        productPrice1.setText("$59.99");
        // productImage1.setImageResource(R.drawable.sneakers);

        // fragment reference
        Fragment itemPageFrag = new itemPage();

        container1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setCurrentFragment(itemPageFrag);
            }

        });

        // ======= Product 2 =======
        View container2 = rootView.findViewById(R.id.include_new_2);
        ImageView productImage2 = container2.findViewById(R.id.product_image);
        TextView productName2 = container2.findViewById(R.id.product_name);
        TextView productPrice2 = container2.findViewById(R.id.product_price);

        productName2.setText("Backpack");
        productPrice2.setText("$39.99");
        // productImage2.setImageResource(R.drawable.backpack);

        container2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setCurrentFragment(itemPageFrag);
            }
        });

        return rootView;
    }
}
