package com.xdorg1.fsdemousercenter.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class UserToken {

    private static final Logger logger = LoggerFactory.getLogger(UserToken.class);
    private static final String CLIENT_ID = "fsdemo-usercenter";
    private static final String CLIENT_CREDENTIAL = "time4@FUN";

    public String access_token = null;
    public String token_type = null;
    public Integer expires_in = 0;
    public String scope = null;
    public String jti = null;

    private static int token_length = 256;

    //RestTemplate在RestConfig当中已经配置了Basic Auth
    static private RestTemplate restTemplate;

    private static final String AUTHSRV_TOKEN_URL = "http://fsdemo-authsrv:8084/oauth/token";

    public UserToken() {

    }

    public UserToken (String token) {
        access_token = token;
    }

    public String getUserToken(){
        return access_token;
    }

    public Boolean isExpired(){
        return false;
    }

    public String toString(){
        return "UserToken: { \n access_token: " + access_token + ",\n token_type: " + token_type + ",\n expires_in: " + expires_in + ",\n scope: " + scope + ",\n jti: " + jti + "\n}";
    }

    //临时使用，在使用authsrv之后就不用这个函数了
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

    /**
     * 在authsrv使用client_credential模式获取授权
     * input: curl -X POST "http://fsdemo-authsrv:8084/oauth/token" --user fsdemo-usercenter:time4@FUN -d "grant_type=client_credentials&scope=write"
     * 期望的output样例:
     * {
     *     "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJ3cml0ZSJdLCJleHAiOjE1ODQyOTExMjIsImp0aSI6ImEwNTZiOGQyLWU1NzMtNDQ5OS1iYzMxLTNiMGM1NjI4NmZhNCIsImNsaWVudF9pZCI6ImZzZGVtby11c2VyY2VudGVyIn0._BNwX0m_CVRS067npluZOGGFnDG9grmhpW9OEZbGrf8",
     *     "token_type": "bearer",
     *     "expires_in": 43199,
     *     "scope": "write",
     *     "jti": "a056b8d2-e573-4499-bc31-3b0c56286fa4"
     * }
     */

    static public UserToken getTokenFromAuthSrvByClientMode(){

        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("grant_type", "client_credentials");
        paramMap.add("scope", "write");

        logger.info("Function getTokenFromAuthSrv() will send POST with payload: " + paramMap);

        RestTemplateBuilder builder = new RestTemplateBuilder();

        restTemplate = builder.basicAuthentication(CLIENT_ID, CLIENT_CREDENTIAL).build();

        String response = restTemplate.postForObject(AUTHSRV_TOKEN_URL, paramMap, String.class);

        logger.info("Got POST response from fsdemo-authsrv /oauth/token: " + response);

        ObjectMapper mapper = new ObjectMapper();
        UserToken userToken = null;

        try {
            userToken = mapper.readValue(response, UserToken.class);
            //复杂情形例如List或者Array等，可以使用下面的方法来进行反实例化
            //userToken = mapper.readValue(response, new TypeReference<UserToken>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return userToken;

    }
}
