package com.example.flipkart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
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
    CategoriesAdapterTwo categoriesAdapterTwo;
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerViewTwo.setLayoutManager(gridLayoutManager);
        recyclerViewTwo.setHasFixedSize(true);

    }

    private void ExploreDataTwo() {
        List<CategoriesItem> categoriesItemList2 = new ArrayList<>();
        categoriesItemList2.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList2.add(new CategoriesItem(R.drawable.down));
        categoriesItemList2.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList2.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList2.add(new CategoriesItem(R.drawable.down));
        categoriesItemList2.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList2.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList2.add(new CategoriesItem(R.drawable.down));
        categoriesItemList2.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList2.add(new CategoriesItem(R.drawable.img_2));
        categoriesItemList2.add(new CategoriesItem(R.drawable.down));
        categoriesItemList2.add(new CategoriesItem(R.drawable.img_2));


        categoriesAdapterTwo = new CategoriesAdapterTwo(categoriesItemList2);
        recyclerViewTwo.setAdapter(categoriesAdapterTwo);
    }

    private void LayoutManage() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void ExploreData() {
        List<CategoriesItem> categoriesItemList = new ArrayList<>();
        categoriesItemList.add(new CategoriesItem(R.drawable.down_bg));
        categoriesItemList.add(new CategoriesItem(R.drawable.down_bg));
        categoriesItemList.add(new CategoriesItem(R.drawable.down_bg));
        categoriesItemList.add(new CategoriesItem(R.drawable.down_bg));
        categoriesItemList.add(new CategoriesItem(R.drawable.down_bg));
        categoriesItemList.add(new CategoriesItem(R.drawable.down_bg));
        categoriesItemList.add(new CategoriesItem(R.drawable.down_bg));
        categoriesItemList.add(new CategoriesItem(R.drawable.down_bg));
        categoriesItemList.add(new CategoriesItem(R.drawable.down_bg));

        categoriesAdapter = new CategoriesAdapter(getContext(),categoriesItemList);
        recyclerView.setAdapter(categoriesAdapter);
    }
}