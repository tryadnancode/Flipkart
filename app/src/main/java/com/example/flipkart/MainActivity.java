package com.example.flipkart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView, recent_recycler, suggest_recycler;
    ScrollAdapter adapter;
    RecentlyAdapter recent_adapter;
    SuggestAdapter suggest_adapter;
    TextView marqueeText1;
    Handler handler;
    Runnable runnable;
    int currentIndex = 0;
    BottomNavigationView bottomNavigationView;
    FrameLayout fragmentContainer;
    View mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();
        BannerData();
        SuggestData();
        LayoutManager();
        MarqueeScroll();
        onClick();
    }

    private void onClick() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();
                    if(itemId == R.id.account){
                        Intent i = new Intent(MainActivity.this,Login.class);
                        startActivity(i);
                        finish();
                        return true;
                }
                    if(itemId == R.id.home){
                        Intent i = new Intent(MainActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                        return true;
                    }
                    if(itemId == R.id.categories){
                        Snackbar.make(findViewById(R.id.bottom_nav),"Categories",Snackbar.LENGTH_SHORT).show();
                        return true;
                    } if(itemId == R.id.explore){
                    Fragment myFragment = new Explore();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main, myFragment).commit();
                        return true;
                    }if(itemId == R.id.cart){
                    Fragment myFragment = new Cart();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main, myFragment).commit();
                    return true;
                    }
                return true;
            }
        });
    }
    private void MarqueeScroll() {
        marqueeText1.setSelected(true);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentIndex == adapter.getItemCount()) {
                    currentIndex = 0;
                }
                recyclerView.smoothScrollToPosition(currentIndex++);
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private void LayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recent_recycler.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        suggest_recycler.setLayoutManager(gridLayoutManager);
    }

    private void SuggestData() {
        List<SuggestItem> suggestItems = new ArrayList<>();
        suggestItems.add(new SuggestItem(R.drawable.down));
        suggestItems.add(new SuggestItem(R.drawable.down));
        suggestItems.add(new SuggestItem(R.drawable.down));
        suggestItems.add(new SuggestItem(R.drawable.down));
        suggestItems.add(new SuggestItem(R.drawable.down));
        suggestItems.add(new SuggestItem(R.drawable.down));
        suggest_adapter = new SuggestAdapter(this, suggestItems);
        suggest_recycler.setAdapter(suggest_adapter);
    }

    private void BannerData() {
        List<BannerItem> bannerItems = new ArrayList<>();
        bannerItems.add(new BannerItem(R.drawable.logo, R.drawable.account_circle));
        bannerItems.add(new BannerItem(R.drawable.logo_bg, R.drawable.camera));
        bannerItems.add(new BannerItem(R.drawable.logo, R.drawable.cart));
        bannerItems.add(new BannerItem(R.drawable.down, R.drawable.mic));

        adapter = new ScrollAdapter(this, bannerItems);
        recyclerView.setAdapter(adapter);

        recent_adapter = new RecentlyAdapter(this, bannerItems);
        recent_recycler.setAdapter(recent_adapter);
    }

    private void findId() {
        recyclerView = findViewById(R.id.recyclerView);
        recent_recycler = findViewById(R.id.recycler_recently_viewed);
        suggest_recycler = findViewById(R.id.suggest_recycler);
        marqueeText1 = findViewById(R.id.marqueeText1);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        fragmentContainer = findViewById(R.id.fragment_container);
        mainActivity = findViewById(R.id.main);
    }
}
