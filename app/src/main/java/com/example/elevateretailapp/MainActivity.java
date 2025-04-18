package com.example.elevateretailapp;

import android.os.Bundle;
import com.example.elevateretailapp.databinding.ActivityMainBinding;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.elevateretailapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // the fragments
        Fragment homeFrag = new HomeFragment();
        Fragment profileFrag = new ProfileFragment();
        Fragment settingsFrag = new SettingsFragment();
        Fragment cartFrag = new CartFragment();

        // Set the default fragment
        setCurrentFragment(homeFrag);

        // Set listener for bottom navigation items
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.Home:
                    selectedFragment = homeFrag;
                    break;
                case R.id.Profile:
                    selectedFragment = profileFrag;
                    break;
                case R.id.Settings:
                    selectedFragment = settingsFrag;
                    break;
                case R.id.Cart:
                    selectedFragment = cartFrag;
                    break;
            }
            if (selectedFragment != null) {
                setCurrentFragment(selectedFragment);
            }
            return true;
        });

        // Adjust for edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ConstraintLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to set the current fragment
    void setCurrentFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FragmentContainer, fragment)
                .addToBackStack(null) // This ensures we can go back to the previous fragment
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search submission
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle text changes
                return false;
            }
        });

        return true;
    }
}
