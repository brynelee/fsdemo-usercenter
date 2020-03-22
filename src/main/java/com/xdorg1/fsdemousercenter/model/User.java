package com.xdorg1.fsdemousercenter.model;

import java.util.Date;

public class User {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int id) { user_id = id; };

    public String getUserToken() { return token; }

    public void setUserToken(String token) { token = token; }

    public Date getTokenCreationTime(){ return token_creation_time; }

    public void setToken_creation_time(Date creation_time){ token_creation_time = creation_time; }

    public String getMobile(){return mobile;}

    public void setMobile(String mobile) {this.mobile = mobile;}

    public Integer getStatus() { return status; }

    public void setStatus(Integer status ) {this.status = status;}

    public String getEmail() { return email; }

    public void setEmail(String email ) { this.email = email; }

    public String getPersonal_id() { return personal_id; }

    public void setPersonal_id(String personal_id) { this.personal_id = personal_id; }

    @Override
    public String toString() {
        return "User [id=" + user_id + ", name=" + username + ", password=" + password + ", user_token=" + token + ", Email=" + email + "]" ;
    }

    private String username;
    private String password;
    private Integer user_id;
    private String token;
    private Date token_creation_time;
    private String mobile;
    private Integer status;
    private String email;
    private String personal_id;

}

