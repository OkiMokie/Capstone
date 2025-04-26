package com.example.elevateretailapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Handler cartHandler = new Handler(Looper.getMainLooper());
    private Runnable cartCheckRunnable;
    private boolean cartWasEmpty = false;

    public CartFragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        Button checkoutButton = view.findViewById(R.id.btnCheckout);
        RecyclerView cartRecycler = view.findViewById(R.id.cartRecyclerView);
        TextView grandTotalTextView = view.findViewById(R.id.totalTextBox);

        List<Product> cartItems = CartManager.getCartItems();
        updateGrandTotal(grandTotalTextView);

        if (cartItems.isEmpty()) {
            scheduleCartReminder();
        }

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
                if (CartManager.getCartItems().isEmpty()) {
                    scheduleCartReminder();
                } else {
                    cancelCartReminder();
                }
            }
        });

        cartRecycler.setAdapter(adapter);
        cartRecycler.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }

    private void scheduleCartReminder() {
        OneTimeWorkRequest reminderRequest = new OneTimeWorkRequest.Builder(CartNotificationWorker.class)
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build();

        WorkManager.getInstance(requireContext()).enqueue(reminderRequest);
    }

    private void cancelCartReminder() {
        WorkManager.getInstance(requireContext()).cancelAllWorkByTag("cartReminder");
    }

    public static void updateGrandTotal(TextView grandTotalTextView) {
        double total = 0.0;
        for (Product p : CartManager.getCartItems()) {
            try {
                double price = Double.parseDouble(p.getPrice().replace("$", ""));
                total += price * p.getQuantity();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        grandTotalTextView.setText(String.format("Total: $%.2f", total));
    }

    @Override
    public void onStop() {
        super.onStop();
        if (CartManager.getCartItems().isEmpty()) {
            scheduleCartReminder();
        }
    }
}
