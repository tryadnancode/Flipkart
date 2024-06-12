package com.example.flipkart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoriesAdapterTwo extends RecyclerView.Adapter<CategoriesAdapterTwo.ViewHolder> {
    List<CategoriesItem> categoriesItems;

    public CategoriesAdapterTwo(List<CategoriesItem> categoriesItems) {
        this.categoriesItems = categoriesItems;
    }

    @NonNull
    @Override
    public CategoriesAdapterTwo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout_two,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapterTwo.ViewHolder holder, int position) {
        CategoriesItem item = categoriesItems.get(position);
        holder.imageView.setImageResource(item.getCategories());
    }

    @Override
    public int getItemCount() {
        return categoriesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.categories_image_two);
        }
    }
}
