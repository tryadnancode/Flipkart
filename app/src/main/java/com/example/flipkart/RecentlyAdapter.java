package com.example.flipkart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecentlyAdapter extends RecyclerView.Adapter<RecentlyAdapter.ViewHolder> {
    Context context;
    List<BannerItem> bannerItem;

    public RecentlyAdapter(Context context, List<BannerItem> bannerItem) {
        this.context = context;
        this.bannerItem = bannerItem;
    }

    @NonNull
    @Override
    public RecentlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recently_viewed_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyAdapter.ViewHolder holder, int position) {
        BannerItem item = bannerItem.get(position);
        holder.imageView1.setImageResource(item.getImage2());
    }

    @Override
    public int getItemCount() {
        return bannerItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.recent_image);
        }
    }
}
