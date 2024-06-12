package com.example.flipkart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {
    RecyclerView recyclerView, recyclerViewTwo;
    CategoriesAdapter categoriesAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView=view.findViewById(R.id.categories_recycle);
        recyclerViewTwo = view.findViewById(R.id.categories_two_recycle);
        ExploreData();
        LayoutManage();
        ExploreDataTwo();
        LayoutManageTwo();
        return view;
    }

    private void LayoutManageTwo() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerViewTwo.setLayoutManager(gridLayoutManager);
    }

    private void ExploreDataTwo() {
        List<CategoriesItem> categoriesItemList = new ArrayList<>();
        categoriesItemList.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));
        categoriesItemList.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));
        categoriesItemList.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));
        categoriesItemList.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList.add(new CategoriesItem(R.drawable.down));

        categoriesAdapter = new CategoriesAdapter(getContext(),categoriesItemList);
        recyclerViewTwo.setAdapter(categoriesAdapter);
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
        categoriesItemList.add(new CategoriesItem(R.drawable.down));

        categoriesAdapter = new CategoriesAdapter(getContext(),categoriesItemList);
        recyclerView.setAdapter(categoriesAdapter);
    }
}