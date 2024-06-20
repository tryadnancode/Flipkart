package com.example.flipkart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {
    Context context;
    List<ResponseProductItem> exploreItems;

    public ExploreAdapter(Context context, List<ResponseProductItem> exploreItems) {
        this.context = context;
        this.exploreItems = exploreItems;
    }

    @NonNull
    @Override
    public ExploreAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.explore_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreAdapter.ViewHolder holder, int position) {
        ResponseProductItem item = exploreItems.get(position);
        Picasso.get().load(item.getImage()).into(holder.imageView);
        holder.price.setText("$"+item.getPrice()+"");
        Rating rating = item.getRating();
        if (rating != null) {
            float rateValue = rating.getRateAsFloat();
            holder.ratingBar.setRating(rateValue);
        } else {
            holder.ratingBar.setRating(0); // Default rating if not available
        }
    }

    @Override
    public int getItemCount() {
        return exploreItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView price;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageExplore);
            ratingBar = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);

        }
    }
}
