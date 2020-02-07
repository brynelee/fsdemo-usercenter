package com.xdorg1.fsdemousercenter.controller;

import com.xdorg1.fsdemousercenter.model.LoginPayload;
import com.xdorg1.fsdemousercenter.model.User;
import com.xdorg1.fsdemousercenter.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;

@RestController
public class UserCenterController {

    private static final Logger logger = LoggerFactory.getLogger(UserCenterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getuserlist")
    public List<User> getUserList()
    {
        List<User> userList = userService.getUserList();
        logger.info("user list is " + userList);
        Iterator<User> it = userList.iterator();
        while (!it.hasNext()){
            User user = it.next();
            logger.info("the user id " + user.getUserId() + " and user name is " + user.getUsername());
        }
        return userList;

    }

    @PostMapping("/login")
    public LoginPayload queryForLogin(String username, String password){
        return userService.queryForLogin(username, password);
    }

    @PostMapping("/adduser")
    public String addUser(User user){
        return userService.addUser(user);
    }

}
