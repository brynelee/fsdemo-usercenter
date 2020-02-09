package com.xdorg1.fsdemousercenter.model;

public class LogoutPayload {
    /*
        error code definition：
        0x1 - logout success
        0x2 - token is illegal
     */

    public static final int LOGOUT_SUCCESS = 0x1;
    public static final int TOKEN_ILLEGAL = 0x2;

    public int errorCode;
}
