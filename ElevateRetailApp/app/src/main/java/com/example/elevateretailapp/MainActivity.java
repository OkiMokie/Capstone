package com.example.elevateretailapp;

import android.os.Bundle;
import com.example.elevateretailapp.databinding.ActivityMainBinding;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.elevateretailapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        Fragment homeFrag = new HomeFragment();
        Fragment profileFrag = new ProfileFragment();
        Fragment settingsFrag = new SettingsFragment();

        setCurrentFragment(homeFrag);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.Home:
                    setCurrentFragment(homeFrag);
                    break;
                case R.id.Profile:
                    setCurrentFragment(profileFrag);
                    break;
                case R.id.Settings:
                    setCurrentFragment(settingsFrag);
                    break;
            }

            return true;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
}