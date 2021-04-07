package com.example.shopappdemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.shopappdemo.customObjects.Item;
import com.example.shopappdemo.customObjects.LoginCredential;
import com.example.shopappdemo.customObjects.UserCredentials;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainRepository {
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;

    static MainRepository instanceRepo;
    static Context mContext;

    static int DATACODE;
    static int IMAGECODE;

    FirebaseFirestore db;
    private static FirebaseAuth mAuth;

    //registration
    static UserCredentials regUserCredential;
    static String regEmail, regPhoneNumber, regPassword, regName;
    static String loginEmail, loginPassword;

    public static MainRepository newInstance(Context context){
        mContext = context;

        if(instanceRepo == null){
            instanceRepo = new MainRepository();
        }
        return instanceRepo;
    }


    public int uploadItemData(Item item){
        String category = item.getCategory();
        db = FirebaseFirestore.getInstance();
        db.collection("product")
                .document("categories")
                .collection(category)
                .add(item)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        DATACODE = SUCCESS;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        DATACODE = FAILURE;
                    }
                });

        return DATACODE;
    }


    public int RegisterUser(UserCredentials userCredentials){
        mAuth = FirebaseAuth.getInstance();

        regUserCredential = userCredentials;
        regEmail = regUserCredential.getEmail();
        regName = regUserCredential.getName();
        regPhoneNumber = regUserCredential.getPhoneNumber();
        regPassword = regUserCredential.getPassword();

        mAuth.createUserWithEmailAndPassword(regEmail, regPassword)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            updaterUserDB();
                        }

                        else{
                            DATACODE = FAILURE;
                            Log.d("inside OnComplete", "Failed");
                        }
                    }
                })
                .addOnFailureListener((Activity) mContext, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        DATACODE = FAILURE;
                        Log.d("Registration Failed", "onFailure: " + e.getMessage());
                    }
                });

        return  DATACODE;
    }


    public int LoginUser(LoginCredential loginCredential){
        mAuth = FirebaseAuth.getInstance();

        loginEmail = loginCredential.getEmail();
        loginPassword = loginCredential.getPassword();

        mAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            DATACODE = SUCCESS;
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                DATACODE = FAILURE;
                Log.d("LOGIN USER FAIL", "onFailure: "+ e.getMessage());
            }
        });

        return DATACODE;
    }

    private void updaterUserDB() {

        db = FirebaseFirestore.getInstance();
        Log.d("email value", "updaterUserDB: " + regEmail);

        db.collection("User")
                .document(regEmail)
                .collection("Registration Details")
                .add(regUserCredential)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    DATACODE = SUCCESS;
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                DATACODE = FAILURE;
                Log.d("User db update fail",e.getMessage());
            }
        });
    }


}