package com.example.shopappdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shopappdemo.customObjects.UserCredentials;

public class RegisterActivity extends AppCompatActivity {

    static int DATACODE;

    EditText emailEdt, passwordEdt, nameEdt, numberEdt;
    Button registerBtn;
    ProgressBar progressBar;
    String email, phoneNumber, name, password;

    RegisterViewModel registerViewModel;

    UserCredentials userCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEdt = findViewById(R.id.RegisterEmail);
        passwordEdt = findViewById(R.id.RegisterPasswordEdt);
        nameEdt = findViewById(R.id.RegisterNameEdt);
        numberEdt = findViewById(R.id.RegisterNumber);
        registerBtn = findViewById(R.id.RegisterUserBtn);
        progressBar = findViewById(R.id.RegisterPogressBar);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEdt.getText().toString().trim();
                name = nameEdt.getText().toString();
                phoneNumber = numberEdt.getText().toString().trim();
                password = passwordEdt.getText().toString();

                if(phoneNumber.isEmpty()){
                    numberEdt.setError("Phone Number is required!");
                    numberEdt.requestFocus();
                    return;
                }

                if(name.isEmpty()){
                    nameEdt.setError("Name is required!");
                    nameEdt.requestFocus();
                    return;
                }

                if(email.isEmpty()){
                    emailEdt.setError("Email is required!");
                    emailEdt.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    passwordEdt.setError("Password is required!");
                    passwordEdt.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEdt.requestFocus();
                    emailEdt.setError("Please provide valid Email!");
                    return;
                }

                userCredentials = new UserCredentials(name, email, phoneNumber, password);
                registerViewModel = RegisterViewModel.getInstance(RegisterActivity.this);
                DATACODE = registerViewModel.registerUser(userCredentials);

                if(DATACODE == 1){
                    Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(RegisterActivity.this, "failure! Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }
}