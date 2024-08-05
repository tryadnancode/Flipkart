package com.example.flipkart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeakSearch extends AppCompatActivity {

    private List<ResponseProductItem> productList;
    private TextView tvResult;
    private ImageView ivProductImage;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_search);
        tvResult = findViewById(R.id.tv_result);
        ivProductImage = findViewById(R.id.iv_product_image);

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);

        Intent intent = getIntent();
        if (intent != null) {
            String spokenText = intent.getStringExtra("spokenText");
            if (spokenText != null) {
                fetchProductsAndSearch(spokenText);
            }
        }
    }

    private void fetchProductsAndSearch(String spokenText) {
        Call<List<ResponseProductItem>> call = apiInterface.getProducts();
        call.enqueue(new Callback<List<ResponseProductItem>>() {
            @Override
            public void onResponse(Call<List<ResponseProductItem>> call, Response<List<ResponseProductItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList = response.body();
                    searchAndDisplayResult(spokenText);
                } else {
                    tvResult.setText("Failed to retrieve products");
                    ivProductImage.setImageDrawable(null);
                }
            }

            @Override
            public void onFailure(Call<List<ResponseProductItem>> call, Throwable t) {
                tvResult.setText("An error occurred");
                ivProductImage.setImageDrawable(null);
            }
        });
    }

    private void searchAndDisplayResult(String spokenText) {
        if (productList != null) {
            List<ResponseProductItem> matchingProducts = new ArrayList<>();
            for (ResponseProductItem item : productList) {
                if (item.getTitle().toLowerCase().contains(spokenText.toLowerCase())) {
                    matchingProducts.add(item);
                }
            }

            if (!matchingProducts.isEmpty()) {
                // Display the first matching product
                ResponseProductItem firstMatch = matchingProducts.get(0);
                tvResult.setText("Found: " + firstMatch.getTitle() + "\n\n" + firstMatch.getDescription());
                Glide.with(this)
                        .load(firstMatch.getImage())
                        .into(ivProductImage);

                // Optionally, handle multiple matches
                if (matchingProducts.size() > 1) {
                    tvResult.append("\n\nOther similar products:");
                    for (int i = 1; i < matchingProducts.size(); i++) {
                        ResponseProductItem item = matchingProducts.get(i);
                        tvResult.append("\n\n" + item.getTitle());
                    }
                }
            } else {
                tvResult.setText("Not Found");
                ivProductImage.setImageDrawable(null);
            }
        } else {
            tvResult.setText("No products available");
            ivProductImage.setImageDrawable(null);
        }
    }
}
