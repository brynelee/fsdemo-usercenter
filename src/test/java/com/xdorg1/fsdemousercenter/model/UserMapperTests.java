package com.xdorg1.fsdemousercenter.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    UserMapper usermapper;

    @Test
    public void testGetUserList(){
        List<User> userlist = usermapper.getUserList();
        System.out.println("The user number is " + userlist.size());
    }

    @Test
    public void testUserQueryNegative(){

        String username = "xiao";

        try {
            int user_id = usermapper.getUserId(username);
        }catch(Exception e){
            System.out.println(e.getClass().toString());
        }

    }

    @Test
    public void testUserQueryPostive(){

        String username = "xiaodong";
        try {
            int user_id = usermapper.getUserId(username);
            System.out.println("xiaodong's user id is " + user_id);
        }catch(Exception e){
            e.getMessage();
        }

    }
}
