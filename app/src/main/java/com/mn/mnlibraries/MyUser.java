package com.mn.mnlibraries;

import com.mn.easyauth.User;
//creating own user class extends libs User class
public class MyUser extends User {

    public MyUser(String token){
        super(token);
    }
}
