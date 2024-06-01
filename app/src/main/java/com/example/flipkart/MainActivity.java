package com.example.flipkart;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView,recent_recycler;
    ScrollAdapter adapter;
    RecentlyAdapter recent_adapter;
    Handler handler;
    Runnable runnable;
    int currentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        recent_recycler=findViewById(R.id.recycler_recently_viewed);

        TextView marqueeText1 = findViewById(R.id.marqueeText1);
        marqueeText1.setSelected(true);
        List<BannerItem> bannerItems = new ArrayList<>();
        bannerItems.add(new BannerItem(R.drawable.logo,R.drawable.account_circle));
        bannerItems.add(new BannerItem(R.drawable.logo_bg,R.drawable.camera));
        bannerItems.add(new BannerItem(R.drawable.logo,R.drawable.cart));
        bannerItems.add(new BannerItem(R.drawable.down,R.drawable.mic));

        adapter = new ScrollAdapter(this, bannerItems);
        recyclerView.setAdapter(adapter);

        recent_adapter = new RecentlyAdapter(this,bannerItems);
        recent_recycler.setAdapter(recent_adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recent_recycler.setLayoutManager(linearLayoutManager);

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
}
