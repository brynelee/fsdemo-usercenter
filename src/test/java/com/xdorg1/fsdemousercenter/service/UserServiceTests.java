package com.xdorg1.fsdemousercenter.service;

import com.xdorg1.fsdemousercenter.model.LoginPayload;
import com.xdorg1.fsdemousercenter.model.LogoutPayload;
import com.xdorg1.fsdemousercenter.model.UserEntity;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class        UserServiceTests {

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

    @Test
    void testGetUserInfoByUsername(){
        String username = "dahai";

        UserEntity userEntity = userService.getUserInfoByUsername(username);
        Assert.assertEquals("dahai_88889999@sina.com", userEntity.getEmail());
        Assert.assertEquals("110101197812128889", userEntity.getPersonal_id());
    }

    @Test
    void logoutTest(){
        String username = "dahai";
        String password = "666666";
        LoginPayload login_payload = userService.queryForLogin(username, password);
        String token = login_payload.userToken;

        LogoutPayload payload = userService.logout(username, token);
        Assert.assertEquals(LogoutPayload.LOGOUT_SUCCESS, payload.errorCode);
    }

}
