package com.example.flipkart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        ApiInterface apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<ResponseProductItem>> listCall = apiInterface.getImage();
        listCall.enqueue(new Callback<List<ResponseProductItem>>() {
            @Override
            public void onResponse(Call<List<ResponseProductItem>> call, Response<List<ResponseProductItem>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<ResponseProductItem> items = filterCategories(response.body());
                    categoriesAdapter = new CategoriesAdapter(getContext(),items);
                    recyclerView.setAdapter(categoriesAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<ResponseProductItem>> call, Throwable t) {
                Log.e("HomeFragment", "onFailure: " + t.getMessage());

            }
        });

    }
    //Getting one item from category
    private List<ResponseProductItem> filterCategories(List<ResponseProductItem> body) {
        Map<String, ResponseProductItem> categoryMap = new LinkedHashMap<>();
        for (ResponseProductItem item : body) {
            if (!categoryMap.containsKey(item.getCategory())) {
                categoryMap.put(item.getCategory(), item);
            }
        }
        return new ArrayList<>(categoryMap.values());
    }
}