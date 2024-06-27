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

public class FlipkartGrocery extends AppCompatActivity {
    AppCompatImageButton flipkart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_flipkart_grocery);

        flipkart = findViewById(R.id.flipkart_mart2);
        flipkart.setOnClickListener(v -> {
            Intent i = new Intent(FlipkartGrocery.this, MainActivity.class);
            startActivity(i);
        });
    }
}