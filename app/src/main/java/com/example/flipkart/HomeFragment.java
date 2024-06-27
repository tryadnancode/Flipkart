package com.example.flipkart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView, recentRecycler, suggestRecycler;
    AppCompatImageButton mart, grocery;
    Button showAll;
    TextView textView;
    ImageView camera, mic;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch aSwitch;
    private Handler handler;
    private Runnable scrollRunnable;
    private int currentPosition = 0;
    private long backPressedTime;
    private Toast backToast;
    ShimmerFrameLayout shimmerFrameLayout;
    private static final int REQUEST_PERMISSION = 300;
    private static final int REQUEST_CAMERA = 100;
    private static final int SPEECH_REQUEST_CODE = 0;
    private SuggestAdapter suggestAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        recyclerView = view.findViewById(R.id.recyclerView);
        recentRecycler = view.findViewById(R.id.recycler_recently_viewed);
        suggestRecycler = view.findViewById(R.id.suggest_recycler);
        textView = view.findViewById(R.id.marqueeText1);
        camera = view.findViewById(R.id.camera);
        mic = view.findViewById(R.id.mic);
        aSwitch = view.findViewById(R.id.switchButton);
        shimmerFrameLayout = view.findViewById(R.id.shimmer2);
        mart = view.findViewById(R.id.flipkart_mart);
        grocery = view.findViewById(R.id.flipkart_grocery);
        grocery.setOnClickListener(v -> {
            Intent i = new Intent(requireContext(), FlipkartGrocery.class);
            startActivity(i);
        });
        showAll = view.findViewById(R.id.show_all);
        showAll.setOnClickListener(v -> {
            if (suggestAdapter.isShowingAll()) {
                suggestAdapter.showDefaultItems();
                suggestAdapter.setShowingAll(false);
                showAll.setText("Show All");
            } else {
                suggestAdapter.showAllItems();
                suggestAdapter.setShowingAll(true);
                showAll.setText("Show Less");
            }
        });
        switchFragment();
        openCamera();
        openMic();
        fetchSuggestedItems();

        textView.setSelected(true);

        ArrayList<BannerItem> bannerList = Constant.getBanner();
        ScrollAdapter rewardBannerAdapter = new ScrollAdapter(bannerList);
        recyclerView.setAdapter(rewardBannerAdapter);
        LinearLayoutManager bannerLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(bannerLayoutManager);

        ArrayList<SuggestItem> suggestItems = Constant.getSuggestItems();
        RecentlyAdapter recentlyAdapter = new RecentlyAdapter(suggestItems);
        recentRecycler.setAdapter(recentlyAdapter);
        LinearLayoutManager suggestItem = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recentRecycler.setLayoutManager(suggestItem);

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

    private void openMic() {
        mic.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak something...");
            startActivityForResult(intent, SPEECH_REQUEST_CODE);
        });
    }

    private void switchFragment() {
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showFragment();
            } else {
                hideFragment();
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
        camera.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA}, REQUEST_PERMISSION);
            } else {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(scrollRunnable);
    }

    OnBackPressedCallback callback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                if (backToast != null) {
                    backToast.cancel();
                }
                requireActivity().finish();
            } else {
                backToast = Toast.makeText(requireContext(), "Press again to exit", Toast.LENGTH_SHORT);
                backToast.show();
            }
            backPressedTime = System.currentTimeMillis();
        }
    };

    private void fetchSuggestedItems() {
        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<ResponseProductItem>> call = apiInterface.getImage();
        call.enqueue(new Callback<List<ResponseProductItem>>() {
            @Override
            public void onResponse(Call<List<ResponseProductItem>> call, Response<List<ResponseProductItem>> response) {
                shimmerFrameLayout.setVisibility(View.GONE);
                suggestRecycler.setVisibility(View.VISIBLE);
                if (response.isSuccessful() && response.body() != null) {
                    List<ResponseProductItem> allUsers = response.body();
                    suggestAdapter = new  SuggestAdapter(allUsers);
                    suggestRecycler.setAdapter(suggestAdapter);
                    suggestRecycler.setVisibility(View.VISIBLE);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
                    suggestRecycler.setLayoutManager(gridLayoutManager);
                } else {
                    Log.e("HomeFragment", "Response unsuccessful: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<ResponseProductItem>> call, Throwable t) {
                Log.e("HomeFragment", "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "Failed to fetch data. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
