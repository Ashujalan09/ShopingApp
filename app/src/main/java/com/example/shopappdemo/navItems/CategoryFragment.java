package com.example.shopappdemo.navItems;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shopappdemo.ItemFragment;
import com.example.shopappdemo.customObjects.Categories;
import com.example.shopappdemo.CategoryRecyclerViewAdaptor;
import com.example.shopappdemo.R;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryRecyclerViewAdaptor.OnCategoryClickListener {

    private CategoryViewModel mViewModel;

    List<String> titles;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    CategoryRecyclerViewAdaptor categoryRecyclerViewAdaptor;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.category_fragment, container, false);
        recyclerView = v.findViewById(R.id.category_recycler);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        Categories category = new Categories();
        titles = category.getCategoryList();

        linearLayoutManager = new LinearLayoutManager(getContext());
        categoryRecyclerViewAdaptor = new CategoryRecyclerViewAdaptor(titles, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categoryRecyclerViewAdaptor);

    }

    @Override
    public void onCategoryClick(String category) {
        Bundle b = new Bundle();
        b.putString("category",category);
        ItemFragment itemFragment = new ItemFragment();
        itemFragment.setArguments(b);
        getParentFragmentManager().beginTransaction().addToBackStack("category").replace(R.id.main_frag_holder, itemFragment ).commit();

    }
}