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

    public CategoryRecyclerViewAdaptor(List<String> titles){
        this.titles = titles;
    }

    public class CategoryRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public CategoryRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.category_recycler_title);
        }
    }

    @NonNull
    @Override
    public CategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_recycler_layout, parent, false);
        return new CategoryRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

}
