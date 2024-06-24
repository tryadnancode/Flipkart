package com.example.flipkart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.textView.setText(item.getCategory());
        holder.clickCategory.setOnClickListener(v -> clickCategory(v,holder.getAdapterPosition()));
    }

    private void clickCategory(View v, int adapterPosition) {
//        Intent i = new Intent(v.getContext(),CategoriesAdapterTwo.class);
//        v.getContext().startActivity(i);
        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return categoriesItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        LinearLayout clickCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.categories_image);
            textView = itemView.findViewById(R.id.category_adapter1);
            clickCategory = itemView.findViewById(R.id.clickCategory);
        }
    }
}
