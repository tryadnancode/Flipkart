package com.example.flipkart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        View view = LayoutInflater.from(context).inflate(R.layout.explore_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreAdapter.ViewHolder holder, int position) {
        ResponseProductItem item = exploreItems.get(position);
        Picasso.get().load(item.getImage()).into(holder.imageView);
        holder.price.setText("$" + item.getPrice() + "");
        holder.category.setText(item.getCategory());
        Rating rating = item.getRating();
        if (rating != null) {
            float rateValue = rating.getRateAsFloat();
            holder.ratingBar.setRating(rateValue);
        } else {
            holder.ratingBar.setRating(0); // Default rating if not available
        }
        holder.clickDetails.setOnClickListener(v -> imageDetails(v, holder.getAdapterPosition()));

}

    private void imageDetails(View v, int adapterPosition) {
        Intent i = new Intent(v.getContext(), ImageDetailsOfExplore_Activity.class);
        i.putExtra("image",exploreItems.get(adapterPosition).getImage());
        i.putExtra("title",exploreItems.get(adapterPosition).getTitle());
        i.putExtra("description",exploreItems.get(adapterPosition).getDescription());
        i.putExtra("price",exploreItems.get(adapterPosition).getPrice()+"");
        Rating rating = exploreItems.get(adapterPosition).getRating();
        if (rating != null) {
            String ratingStr = String.valueOf(rating.getRateAsFloat());
            i.putExtra("rating", ratingStr);
        }
         v.getContext().startActivity(i);
    }

    @Override
    public int getItemCount() {
        return exploreItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView price,category;
        RatingBar ratingBar;
        LinearLayout clickDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageExplore);
            ratingBar = itemView.findViewById(R.id.rating);
            price = itemView.findViewById(R.id.price);
            clickDetails = itemView.findViewById(R.id.click_details);
            category = itemView.findViewById(R.id.exploreCategory);
        }
    }
}
