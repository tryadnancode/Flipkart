package com.example.flipkart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroceryCategoryAdapter extends RecyclerView.Adapter<GroceryCategoryAdapter.ViewHolder> {
    private ArrayList<GroceryItemCategory> list;

    public GroceryCategoryAdapter(ArrayList<GroceryItemCategory> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GroceryCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_category_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryCategoryAdapter.ViewHolder holder, int position) {
        GroceryItemCategory item = list.get(position);
        holder.imageView.setImageResource(item.getImg());
        holder.textView.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.grocery_img);
            textView = itemView.findViewById(R.id.groceryName);
        }
    }
}
