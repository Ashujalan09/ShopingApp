package com.example.shopappdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryRecyclerViewAdaptor extends RecyclerView.Adapter<CategoryRecyclerViewAdaptor.CategoryRecyclerViewHolder> {

    List<String> titles;
    OnCategoryClickListener onCategoryClickListener;

    public CategoryRecyclerViewAdaptor(List<String> titles, OnCategoryClickListener onCategoryClickListener){
        this.titles = titles;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    public class CategoryRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        OnCategoryClickListener onCategoryClickListener;
        public CategoryRecyclerViewHolder(@NonNull View itemView, OnCategoryClickListener onCategoryClickListener) {
            super(itemView);
            this.onCategoryClickListener = onCategoryClickListener;
            title = itemView.findViewById(R.id.category_recycler_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String selectedCategory = titles.get(getAdapterPosition());
            onCategoryClickListener.onCategoryClick(selectedCategory);
        }
    }

    @NonNull
    @Override
    public CategoryRecyclerViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_recycler_layout, parent, false);

        return new CategoryRecyclerViewHolder(view, onCategoryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public interface OnCategoryClickListener{
        void onCategoryClick(String category);
    }

}
