package com.example.shopappdemo;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.shopappdemo.customObjects.LoginCredential;
import com.example.shopappdemo.customObjects.UserCredentials;

public class LoginViewModel extends ViewModel {


    static MainRepository mainRepository;
    static LoginViewModel instance;
    static Context mContext;
    static int DATACODE;


    public static LoginViewModel getInstance(Context context){
        mContext = context;
        if(instance == null){
            instance = new LoginViewModel();
        }
        return instance;
    }

    public int loginUser(LoginCredential loginCredential){
        mainRepository = MainRepository.newInstance(mContext);
        DATACODE = mainRepository.LoginUser(loginCredential);

        return DATACODE;
    }

}
