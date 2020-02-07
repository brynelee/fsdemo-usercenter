package com.xdorg1.fsdemousercenter.model;

import java.util.Date;
import java.util.Random;

public class UserToken {

    private String tokenString;
    private Date tokenCreationDate;
    private static int token_length = 256;


    public UserToken() {
        tokenString = createTokenString();
    }

    public UserToken(String token){
        tokenString = token;
    }

    public String getUserToken(){
        return tokenString;
    }

    public Boolean isExpired(){
        return false;
    }

    //length用户要求产生字符串的长度
    private static String createTokenString(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<token_length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
