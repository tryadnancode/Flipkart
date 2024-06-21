package com.example.flipkart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class ImageDetailsOfExplore_Activity extends AppCompatActivity {
    ImageView imageView,back;
    TextView title,description,price,product;
    String title_st,img_st,description_st,price_st,rating_st;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details_of_explore);
        imageView = findViewById(R.id.img);
        title = findViewById(R.id.title_d);
        description = findViewById(R.id.discription_d);
        price = findViewById(R.id.price_d);
        ratingBar = findViewById(R.id.rating2);
        product = findViewById(R.id.product_name);
        back = findViewById(R.id.back);
        Intent i = getIntent();
        img_st = i.getStringExtra("image");
        Picasso.get().load(img_st).into(imageView);

        title_st =String.valueOf(i.getStringExtra("title"));
        title.setText(title_st);

        description_st = String.valueOf(i.getStringExtra("description"));
        description.setText(description_st);

        price_st = String.valueOf(i.getStringExtra("price"));
        price.setText("$"+price_st);

        rating_st = String.valueOf(i.getStringExtra("rating"));
        ratingBar.setRating(Float.parseFloat(rating_st));

        back.setOnClickListener(v -> onBackPressed());
//        product.setText(title_st);
        
        imageView.setOnClickListener(v -> {
            Intent zoomIntent = new Intent(ImageDetailsOfExplore_Activity.this, ZoomImageActivity.class);
            zoomIntent.putExtra("image", img_st);
            startActivity(zoomIntent);
        });
    }
}