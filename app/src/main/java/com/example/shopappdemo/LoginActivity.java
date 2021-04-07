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

import com.example.shopappdemo.customObjects.LoginCredential;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    LoginViewModel loginViewModel;

    static int DATACODE;

    EditText emailEdt, passowrdEdt;
    Button loginBtn, forgetPwdBtn, registerBtn;
    ProgressBar progressBar;
    String email, password;

    LoginCredential credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        emailEdt = findViewById(R.id.LoginEmailEdtText);
        passowrdEdt = findViewById(R.id.LoginPasswordEdtText);
        loginBtn = findViewById(R.id.LoginButton);
        forgetPwdBtn = findViewById(R.id.LoginForgetPwdBtn);
        registerBtn = findViewById(R.id.LoginRegisterBtn);
        progressBar = findViewById(R.id.LoginProgressBar);


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgetPwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailEdt.getText().toString();
                password = passowrdEdt.getText().toString();

                if(email.isEmpty()){
                    emailEdt.setError("Email is required! ");
                    emailEdt.requestFocus();
                    return;
                }

                if(password.isEmpty()){
                    passowrdEdt.setError("Password is required!");
                    passowrdEdt.requestFocus();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEdt.requestFocus();
                    emailEdt.setError("Please provide valid Email");
                    return;
                }

                credentials = new LoginCredential(email, password);

                loginViewModel = LoginViewModel.getInstance(LoginActivity.this);
                DATACODE = loginViewModel.loginUser(credentials);

                if(DATACODE == 1){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                }
        }
        });

    }

}