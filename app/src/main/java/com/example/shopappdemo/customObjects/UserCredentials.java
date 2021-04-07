package com.example.shopappdemo.customObjects;

public class UserCredentials {
    String name;
    String email;
    String PhoneNumber;
    String Password;

    public UserCredentials(){}

    public UserCredentials(String name, String email, String phoneNumber, String password) {
        this.name = name;
        this.email = email;
        PhoneNumber = phoneNumber;
        Password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getPassword() {
        return Password;
    }
}
