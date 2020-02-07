package com.xdorg1.fsdemousercenter.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTokenTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testTokenCreation(){

        UserToken ut = new UserToken();

        System.out.println(ut.getUserToken());
    }
}
