package com.example.shopappdemo.customObjects;

public class LoginCredential {
    String email;
    String password;

    public LoginCredential(){}

    public LoginCredential(String email, String password) {

        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
