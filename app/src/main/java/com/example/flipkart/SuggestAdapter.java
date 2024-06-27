package com.example.flipkart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SuggestAdapter extends RecyclerView.Adapter<SuggestAdapter.ViewHolder> {
    private List<ResponseProductItem> list;
    private List<ResponseProductItem> fullList;
    private static final int DEFAULT_ITEM_COUNT = 3;
    private boolean isShowingAll = false;

    public SuggestAdapter(List<ResponseProductItem> list) {
        this.fullList = list;
        this.list = new ArrayList<>(list.subList(0, Math.min(DEFAULT_ITEM_COUNT, list.size())));
    }

    @NonNull
    @Override
    public SuggestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggest_grid_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestAdapter.ViewHolder holder, int position) {
        ResponseProductItem suggestItem = list.get(position);
        Picasso.get().load(suggestItem.getImage()).into(holder.imageView);
        holder.textView.setText(suggestItem.getCategory());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void showAllItems() {
        list = new ArrayList<>(fullList);
        notifyDataSetChanged();
    }

    public void showDefaultItems() {
        list = new ArrayList<>(fullList.subList(0, Math.min(DEFAULT_ITEM_COUNT, fullList.size())));
        notifyDataSetChanged();
    }

    public boolean isShowingAll() {
        return isShowingAll;
    }

    public void setShowingAll(boolean showingAll) {
        isShowingAll = showingAll;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.category_suggest);
        }
    }
}
