package com.example.flipkart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExploreFragment extends Fragment {
    RecyclerView recyclerView;
    ExploreAdapter exploreAdapter;
    ShimmerFrameLayout shimmerFrameLayout;
    ImageView search,mic,camera,cart;
    private static final int REQUEST_PERMISSION = 300;
    private static final int REQUEST_CAMERA = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        recyclerView = view.findViewById(R.id.explore_recycle);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        search = view.findViewById(R.id.search);
        mic = view.findViewById(R.id.mic1);
        camera = view.findViewById(R.id.camera101);
        cart = view.findViewById(R.id.cart101);
        ExploreData();
        LayoutManage();
        onClick();
        return view;
    }

    private void onClick() {
        search.setOnClickListener(v -> {
            Intent i = new Intent(requireContext(), SearchActivity.class);
            startActivity(i);
        });
        cart.setOnClickListener(v -> {
            // Navigate to CartFragment
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new CartFragment());
            fragmentTransaction.commit();
        });
        camera.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA}, REQUEST_PERMISSION);
            } else {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });
        mic.setOnClickListener(v -> {
            Intent i = new Intent(requireContext(), SpeakSearch.class);
            startActivity(i);
        });
    }
    private void LayoutManage() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }


    private void ExploreData() {

        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<ResponseProductItem>> listCall = apiInterface.getImage();
        listCall.enqueue(new Callback<List<ResponseProductItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<ResponseProductItem>> call, @NonNull Response<List<ResponseProductItem>> response) {
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                if (response.isSuccessful() && response.body() != null) {
                    List<ResponseProductItem> items = response.body();
                    exploreAdapter = new ExploreAdapter(getContext(), items);
                    recyclerView.setAdapter(exploreAdapter);
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<ResponseProductItem>> call, @NonNull Throwable t) {
                Log.e("HomeFragment", "onFailure: " + t.getMessage());

            }
        });
    }
}

