package com.xdorg1.fsdemousercenter.model;

import java.util.Date;

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

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int id) { user_id = id; };

    public String getUserToken() { return token; }

    public void setUserToken(String token) { token = token; }

    public Date getTokenCreationTime(){ return token_creation_time; }

    public void setToken_creation_time(Date creation_time){ token_creation_time = creation_time; }

    @Override
    public String toString() {
        return "User [id=" + user_id + ", name=" + username + ", password=" + userpassword + ", user_token=" + token + "]" ;
    }

    private String username;
    private String userpassword;
    private Integer user_id;
    private String token;
    private Date token_creation_time;

}

