package com.example.shopappdemo;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.shopappdemo.customObjects.UserCredentials;

public class RegisterViewModel extends ViewModel {

    static MainRepository mainRepository;
    static RegisterViewModel instance;
    static Context mContext;
    static int DATACODE;

    public static RegisterViewModel getInstance(Context context){
        mContext = context;
        if(instance == null){
            instance = new RegisterViewModel();
        }
        return instance;
    }

    public int registerUser(UserCredentials userCredentials){
        mainRepository = MainRepository.newInstance(mContext);
        DATACODE = mainRepository.RegisterUser(userCredentials);

        return DATACODE;
    }

    
}
