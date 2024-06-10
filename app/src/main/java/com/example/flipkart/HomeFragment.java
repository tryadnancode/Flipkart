package com.example.flipkart;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView recentRecycler;
    RecyclerView suggestRecycler;
    TextView textView;
    private Handler handler;
    private Runnable scrollRunnable;
    private int currentPosition = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recentRecycler = view.findViewById(R.id.recycler_recently_viewed);
        suggestRecycler = view.findViewById(R.id.suggest_recycler);
        textView = view.findViewById(R.id.marqueeText1);

        // Enable marquee effect
        textView.setSelected(true);

        // Set up RecyclerView for reward banners
        ArrayList<BannerItem> bannerList = Constant.getBanner();
        ScrollAdapter rewardBannerAdapter = new ScrollAdapter(bannerList);
        recyclerView.setAdapter(rewardBannerAdapter);
        LinearLayoutManager bannerLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(bannerLayoutManager);

        ArrayList<SuggestItem> bannerItem = Constant.getSuggestItems();
        RecentlyAdapter recentlyAdapter = new RecentlyAdapter(bannerItem);
        recentRecycler.setAdapter(recentlyAdapter);
        LinearLayoutManager suggestItem = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recentRecycler.setLayoutManager(suggestItem);

        // Set up RecyclerView for suggestRecycler
        ArrayList<SuggestItem> trendList = Constant.getSuggest();
        SuggestAdapter suggestAdapter = new SuggestAdapter(trendList);
        suggestRecycler.setAdapter(suggestAdapter);
        int COLUMN_COUNT2 = 3;
        suggestRecycler.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), COLUMN_COUNT2, GridLayoutManager.VERTICAL, false);
        suggestRecycler.setLayoutManager(gridLayoutManager);

        // Auto-scroll setup for the banner RecyclerView
        handler = new Handler();
        scrollRunnable = new Runnable() {
            @Override
            public void run() {
                if (currentPosition == rewardBannerAdapter.getItemCount()) {
                    currentPosition = 0;
                }
                recyclerView.smoothScrollToPosition(currentPosition++);
                handler.postDelayed(this, 2000); // Scroll every 3 seconds
            }
        };
        handler.postDelayed(scrollRunnable, 1000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Stop the auto-scrolling when the fragment is destroyed
        handler.removeCallbacks(scrollRunnable);
    }
}
