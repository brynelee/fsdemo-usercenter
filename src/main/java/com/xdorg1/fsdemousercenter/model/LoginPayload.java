package com.xdorg1.fsdemousercenter.model;

public class LoginPayload {
    /*
        error code definition：
        0x1 - login success
        0x2 - password is not correct
        0x3 - user not exist
        0x4 - token expired
     */

    public static final int LOGIN_SUCCESS = 0x1;
    public static final int PASSWORD_INCORRECT = 0x2;
    public static final int USER_NOT_EXIST = 0x3;
    public static final int TOKEN_EXPIRED = 0x4;

    public static final String MSG_PASSWORD_INCORRECT = "Password provided is incorrect.";
    public static final String MSG_USER_NOT_EXIST = "User not exist, please verify or register.";
    public static final String MSG_TOKEN_EXPIRED = "Token expired.";

    public int errorCode;
    public String errorMessage;
    public String username;
    public String userToken;
}
