package com.example.elevateretailapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // SharedPreferences key
    private static final String PREFS_NAME = "AppSettingsPrefs";
    private static final String NOTIFICATIONS_ENABLED_KEY = "notifications_enabled";

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Exit button to return to HomeFragment
        ImageButton exitButton = view.findViewById(R.id.closeSettingBtn);
        exitButton.setOnClickListener(v ->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.FragmentContainer, new HomeFragment())
                        .commit()
        );

        // Setup Notification Switch
        Switch notificationSwitch = view.findViewById(R.id.notificationSwitch);
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Load saved preference
        boolean notificationsEnabled = prefs.getBoolean(NOTIFICATIONS_ENABLED_KEY, true);
        notificationSwitch.setChecked(notificationsEnabled);

        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean(NOTIFICATIONS_ENABLED_KEY, isChecked).apply();

            if (isChecked) {
                Toast.makeText(getContext(), "Notifications enabled", Toast.LENGTH_SHORT).show();
                // Start or re-enable notifications if needed
            } else {
                Toast.makeText(getContext(), "Notifications disabled", Toast.LENGTH_SHORT).show();
                // Stop notifications here (e.g., cancel alarms, unsubscribe from topics)
                stopNotifications();
            }
        });

        return view;
    }

    private void stopNotifications() {
        // Placeholder: stop your scheduled notifications here.
        // Example: cancel AlarmManager, WorkManager, or unsubscribe from Firebase topic.
        // This method should contain the actual code that disables your app's notifications.
    }
}
