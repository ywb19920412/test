package com.example.ywb.mytreasure.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/7/21.
 */
public class User {
    @SerializedName("UserName")
    private String username;
    @SerializedName("Password")
    private String password;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
