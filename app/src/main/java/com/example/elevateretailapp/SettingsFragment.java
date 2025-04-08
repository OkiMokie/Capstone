
package com.example.elevateretailapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        // Mokie:
        // Inflate the layout for this fragment
        // this used to be "return inflater.inflate(R.layout.fragment_settings, container, false);", but instead of this we want to make it an instance we can use rn
        // that's why we declared it as a View object we can reference (View view)
        View myView = inflater.inflate(R.layout.fragment_settings, container, false);

        //Reference the button / Mokie: the reason why you couldn't reference your button was because you didn't edit the code above to not return view, but instead make an instance
        // notice how the object myView can now find your button! Because earlier we passed the container holding the button (fragment_settings)
        ImageButton closeSetting = myView.findViewById((R.id.closeSettingBtn));

        // Mokie: add the listener, the thing that makes the button work
        // you can start typing the .setOnClickListener(new View...) and press tab when it gives you the auto-finish
        // now you can make more buttons in this fashion, just reference the button you want to make work and create the function that its hooked up to

        // this is the function the button is hooked up to vvvv
        closeSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // use this function that was made in mainActivity, just copy it and pass the fragment you want to use
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).setCurrentFragment(new HomeFragment());
                }
            }
        });

        return myView;
    }
}