package com.example.flipkart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class ExploreFragment extends Fragment {
   RecyclerView recyclerView;
   ExploreAdapter exploreAdapter;

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_explore, container, false);
            recyclerView=view.findViewById(R.id.explore_recycle);
            ExploreData();
            LayoutManage();

            return view;
        }

    private void LayoutManage() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }


    private void ExploreData() {
        List<ExploreItem> exploreItems = new ArrayList<>();
        exploreItems.add(new ExploreItem(R.drawable.down));
        exploreItems.add(new ExploreItem(R.drawable.down));
        exploreItems.add(new ExploreItem(R.drawable.down));
        exploreItems.add(new ExploreItem(R.drawable.down));


        exploreAdapter = new ExploreAdapter(getContext(),exploreItems);
        recyclerView.setAdapter(exploreAdapter);
    }
}