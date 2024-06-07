package com.example.flipkart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        getDataFromApi();
        recent_recycler=findViewById(R.id.recycler_recently_viewed);
    }

    private void getDataFromApi() {
        Call<List<ResponseModelItem>> call=RetrofitClient.getInstance().getApi().getUsers();
        call.enqueue(new Callback<List<ResponseModelItem>>() {
            @Override
            public void onResponse(Call<List<ResponseModelItem>> call, Response<List<ResponseModelItem>> response) {
                if(response.isSuccessful()){

                    if(response.body() != null){
                       getItem(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModelItem>> call, Throwable t) {
                Log.e("MainActivity", "onFailure: " + t.getMessage());
            }
        });
    }
    private void getItem(List<ResponseModelItem> itemList) {
        RecentlyAdapter recentlyAdapter = new RecentlyAdapter(this, itemList);
        recent_recycler.setAdapter(recentlyAdapter);
        recent_recycler.setHasFixedSize(true);
        recent_recycler.smoothScrollToPosition(0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recent_recycler.setLayoutManager(linearLayoutManager);

    }

    private void onClick() {
        bottomNavigationView.setOnItemSelectedListener(item -> {

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
                    Fragment myFragment = new Categories();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.main, myFragment).commit();                        return true;
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

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recent_recycler.setLayoutManager(linearLayoutManager);

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
        bannerItems.add(new BannerItem(R.drawable.logo));
        bannerItems.add(new BannerItem(R.drawable.logo_bg));
        bannerItems.add(new BannerItem(R.drawable.logo));
        bannerItems.add(new BannerItem(R.drawable.down));

        adapter = new ScrollAdapter(this, bannerItems);
        recyclerView.setAdapter(adapter);


    }

    private void findId() {
        recyclerView = findViewById(R.id.recyclerView);
        suggest_recycler = findViewById(R.id.suggest_recycler);
        marqueeText1 = findViewById(R.id.marqueeText1);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        fragmentContainer = findViewById(R.id.fragment_container);
        mainActivity = findViewById(R.id.main);
    }
}
