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

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    Context context;
    List<ResponseProductItem>  categoriesItems;

    public CategoriesAdapter(Context context, List<ResponseProductItem> categoriesItems) {
        this.context = context;
        this.categoriesItems = categoriesItems;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.categories_layout,parent,false);
       return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        ResponseProductItem item = categoriesItems.get(position);
//        holder.imageView.setImageResource(item.getCategories());
        Picasso.get().load(item.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoriesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.categories_image);
        }
    }
}
