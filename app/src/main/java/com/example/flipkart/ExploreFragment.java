package com.example.flipkart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ExploreFragment extends Fragment {
    RecyclerView recyclerView;
    ExploreAdapter exploreAdapter;
    ShimmerFrameLayout shimmerFrameLayout;
    ImageView search,mic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        recyclerView = view.findViewById(R.id.explore_recycle);
        shimmerFrameLayout = view.findViewById(R.id.shimmer);
        search = view.findViewById(R.id.search);
        mic = view.findViewById(R.id.mic1);
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