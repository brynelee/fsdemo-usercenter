package com.xdorg1.fsdemousercenter.model;

public class User {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public int getUser_id() {
        return user_id;
    }

    @Override
    public String toString() {
        return "User [id= " + user_id + ", name= " + username + "]";
    }

    public String username;
    public String userpassword;
    public Integer user_id;

}

