package com.example.flipkart;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpeakSearch extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
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
        Button voiceButton = findViewById(R.id.voiceButton);

        voiceButton.setOnClickListener(v -> startVoiceRecognition());

        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
        fetchProducts();
    }

    private void fetchProducts() {
        Call<List<ResponseProductItem>> call = apiInterface.getProducts();
        call.enqueue(new Callback<List<ResponseProductItem>>() {
            @Override
            public void onResponse(Call<List<ResponseProductItem>> call, Response<List<ResponseProductItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    productList = response.body();
                } else {
                    Toast.makeText(SpeakSearch.this, "Failed to retrieve products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseProductItem>> call, Throwable t) {
                Toast.makeText(SpeakSearch.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");

        try {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        } catch (Exception e) {
            Toast.makeText(this, "Speech recognition is not supported on your device", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result != null && !result.isEmpty()) {
                    String spokenText = result.get(0);
                    searchAndDisplayResult(spokenText);
                }
            }
        }
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