package com.example.flipkart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecentlyAdapter extends RecyclerView.Adapter<RecentlyAdapter.ViewHolder> {
    Context context;
    List<ResponseModelItem> recentItems;

    public RecentlyAdapter(Context context, List<ResponseModelItem> recentItems) {
        this.context = context;
        this.recentItems = recentItems;
    }

    @NonNull
    @Override
    public RecentlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recently_viewed_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyAdapter.ViewHolder holder, int position) {
        ResponseModelItem item = recentItems.get(position);
//        holder.imageView1.setImageResource(item.getImage2());
        Picasso.get().load(item.getThumbnailUrl()).into(holder.imageView1);
    }

    @Override
    public int getItemCount() {
        return recentItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.recent_image);
        }
    }
}
