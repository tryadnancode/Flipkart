package com.example.flipkart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Categories extends Fragment {
    RecyclerView recyclerView;
    CategoriesAdapter categoriesAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView=view.findViewById(R.id.categories_recycle);
        ExploreData();
        LayoutManage();
        return view;
    }

    private void LayoutManage() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void ExploreData() {
        List<CategoriesItem> categoriesItemList = new ArrayList<>();
        categoriesItemList.add(new CategoriesItem(R.drawable.down));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));

        categoriesAdapter = new CategoriesAdapter(getContext(),categoriesItemList);
        recyclerView.setAdapter(categoriesAdapter);
    }
}