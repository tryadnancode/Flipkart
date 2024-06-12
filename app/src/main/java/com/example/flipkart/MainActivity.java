package com.example.flipkart;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    CoordinatorLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentContainer = findViewById(R.id.fragment_container);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    HomeFragment homeFragment = new HomeFragment();
    ExploreFragment exploreFragment = new ExploreFragment();
    CartFragment cartFragment = new CartFragment();
    CategoriesFragment categoriesFragment = new CategoriesFragment();
    LoginFragment loginFragment = new LoginFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, homeFragment)
//                        .addToBackStack(null) // Add this line
                        .commit();
                return true;
            case R.id.explore:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, exploreFragment)
                        .addToBackStack(null) // Add this line
                        .commit();
                return true;
            case R.id.cart:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, cartFragment)
                        .addToBackStack(null) // Add this line
                        .commit();
                return true;
            case R.id.categories:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, categoriesFragment)
                        .addToBackStack(null) // Add this line
                        .commit();
                return true;
            case R.id.account:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, loginFragment)
                        .addToBackStack(null) // Add this line
                        .commit();
                return true;
        }
        return false;
    }


}
