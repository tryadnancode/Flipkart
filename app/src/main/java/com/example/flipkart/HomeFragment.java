package com.example.flipkart;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView, recentRecycler, suggestRecycler;
    TextView textView;
    ImageView camera;
    Switch aSwitch;
    private Handler handler;
    private Runnable scrollRunnable;
    private int currentPosition = 0;
    private long backPressedTime;
    private Toast backToast;
    private static final int REQUEST_PERMISSION = 300;
    private static final int REQUEST_CAMERA = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        recyclerView = view.findViewById(R.id.recyclerView);
        recentRecycler = view.findViewById(R.id.recycler_recently_viewed);
        suggestRecycler = view.findViewById(R.id.suggest_recycler);
        textView = view.findViewById(R.id.marqueeText1);
        camera = view.findViewById(R.id.camera);
        aSwitch = view.findViewById(R.id.switchButton);
        switchFragment();
        openCamera();

        // Enable marquee effect
        textView.setSelected(true);

        // Set up RecyclerView for reward banners
        ArrayList<BannerItem> bannerList = Constant.getBanner();
        ScrollAdapter rewardBannerAdapter = new ScrollAdapter(bannerList);
        recyclerView.setAdapter(rewardBannerAdapter);
        LinearLayoutManager bannerLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(bannerLayoutManager);

        // Set up RecyclerView for recentRecycler
        ArrayList<SuggestItem> bannerItem = Constant.getSuggestItems();
        RecentlyAdapter recentlyAdapter = new RecentlyAdapter(bannerItem);
        recentRecycler.setAdapter(recentlyAdapter);
        LinearLayoutManager suggestItem = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recentRecycler.setLayoutManager(suggestItem);

        // Set up RecyclerView for suggestRecycler
        ArrayList<SuggestItem> trendList = Constant.getSuggest();
        SuggestAdapter suggestAdapter = new SuggestAdapter(trendList);
        suggestRecycler.setAdapter(suggestAdapter);
        suggestRecycler.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
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

    private void switchFragment() {
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    showFragment();
                } else {
                    hideFragment();
                }
            }
        });

    }

    private void showFragment() {
        SwitchFragment fragment = new SwitchFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
        aSwitch.setVisibility(View.VISIBLE);
    }

    private void hideFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
        aSwitch.setVisibility(View.GONE);
    }

    private void openCamera() {
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.CAMERA}, REQUEST_PERMISSION);
                } else {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, REQUEST_CAMERA);
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Stop the auto-scrolling when the fragment is destroyed
        handler.removeCallbacks(scrollRunnable);
    }

    OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                if (backToast != null) {
                    backToast.cancel();
                }
                requireActivity().finish(); // Finish the activity
            } else {
                backToast = Toast.makeText(requireContext(), "Press again to exit", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressedTime = System.currentTimeMillis();
        }
    };
}
