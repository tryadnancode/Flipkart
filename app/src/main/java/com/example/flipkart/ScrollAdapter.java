package com.example.flipkart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ScrollAdapter extends RecyclerView.Adapter<ScrollAdapter.ViewHolder> {
    Context context;
    List<BannerItem> bannerItem;

    public ScrollAdapter(Context context, List<BannerItem> bannerItem) {
        this.context = context;
        this.bannerItem = bannerItem;
    }

    @NonNull
    @Override
    public ScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.scroll_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScrollAdapter.ViewHolder holder, int position) {
        BannerItem item = bannerItem.get(position);
        holder.imageView.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return bannerItem.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.scroll_image);

        }
    }
}
