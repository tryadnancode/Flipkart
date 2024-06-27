package com.example.flipkart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EdgeEffect;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FlipkartGrocery extends AppCompatActivity {
    RecyclerView category_recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_flipkart_grocery);
        category_recycler = findViewById(R.id.grocery_category_recyclerview);
        AppCompatImageButton flipkart = findViewById(R.id.flipkart_mart2);
        flipkart.setOnClickListener(v -> {
            Intent i = new Intent(FlipkartGrocery.this, MainActivity.class);
            startActivity(i);
        });
        ArrayList<GroceryItemCategory> items = Constant.getGroceryItem();
        GroceryCategoryAdapter groceryCategoryAdapter = new GroceryCategoryAdapter(items);
        category_recycler.setAdapter(groceryCategoryAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        category_recycler.setLayoutManager(gridLayoutManager);
    }
}