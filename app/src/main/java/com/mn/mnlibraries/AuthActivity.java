package com.mn.mnlibraries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mn.easyauth.EasyAuth;

public class AuthActivity extends AppCompatActivity {
    EasyAuth<MyUser> easyAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        easyAuth = new EasyAuth<>(this, MyUser.class);

        //if some user has logged in or we saved some user
        //needs to use this function only one time when launching the app
        if (easyAuth.hasUser()){
            System.out.println(easyAuth.getUser());
        }else {
            login();

        }

    }

    void login(){
        easyAuth.saveUser(new MyUser("token"));
    }
}