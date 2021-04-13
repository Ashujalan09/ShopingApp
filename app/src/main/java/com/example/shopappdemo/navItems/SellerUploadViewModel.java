package com.example.shopappdemo.navItems;

import android.content.Context;
import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.shopappdemo.MainRepository;
import com.example.shopappdemo.customObjects.Item;

public class SellerUploadViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    MainRepository mainRepository;
    static SellerUploadViewModel instance;
    static Context mContext;
    static int DATACODE;
    static  int IMAGECODE;


    public static SellerUploadViewModel getInstance(Context context){

        mContext = context;

        if(instance == null){
            instance = new SellerUploadViewModel();
        }

        return instance;
    }



    public int uploadItemData(Item item){
        mainRepository = MainRepository.newInstance(mContext);
        DATACODE = mainRepository.uploadItemData(item);

        return DATACODE;
    }

    public int uploadItemImage(Uri uri, String name){
        mainRepository = MainRepository.newInstance(mContext);
        IMAGECODE = mainRepository.uploadItemImage(uri, name);

        return IMAGECODE;
    }

}