package com.xdorg1.fsdemousercenter.service;

import com.xdorg1.fsdemousercenter.model.LoginPayload;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    void testQueryForLoginSuccess(){
        String username = "dahai";
        String password = "666666";

        LoginPayload payload = userService.queryForLogin(username, password);
        Assert.assertEquals(LoginPayload.LOGIN_SUCCESS, payload.errorCode);
        Assert.assertNotNull(payload.userToken);
        System.out.println("the token of dahai is " + payload.userToken);
    }

    @Test
    void testQueryForLoginFailure(){
        String username = "dahai";
        String password = "888888";

        LoginPayload payload = userService.queryForLogin(username, password);
        Assert.assertEquals(LoginPayload.PASSWORD_INCORRECT, payload.errorCode);
        Assert.assertNull(payload.userToken);
        Assert.assertNotNull(payload.errorMessage);
    }
}
