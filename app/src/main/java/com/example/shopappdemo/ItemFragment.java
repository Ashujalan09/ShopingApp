package com.example.shopappdemo;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class ItemFragment extends Fragment {

    private ItemViewModel mViewModel;
    String selectedCategory;
    Bundle b;

    public static ItemFragment newInstance() {
        return new ItemFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        b = this.getArguments();
        //what if B is null
        if(b != null){
            selectedCategory = b.getString("category");
        }
        int test = 0;
        test = mViewModel.GetCategoryItems(selectedCategory);
        if(test != 0){
            Log.d("ItemFragment", "onActivityCreated: success motherfucker");
            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();


    }

}