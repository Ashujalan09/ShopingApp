package com.example.shopappdemo;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {
    static ItemViewModel instance;
    static Context mContext;
    MainRepository mainRepository;
    static int SUCCESSCODE;

    public static ItemViewModel instance(Context context){
        mContext = context;
        if(instance == null){
            instance = new ItemViewModel();
        }
        return instance;
    }


    public int GetCategoryItems(String category) {
        mainRepository = MainRepository.newInstance(mContext);
        SUCCESSCODE = mainRepository.GetCategoryItems(category);
        return SUCCESSCODE;
    }
}
