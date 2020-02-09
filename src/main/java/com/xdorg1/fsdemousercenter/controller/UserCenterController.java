package com.xdorg1.fsdemousercenter.controller;

import com.xdorg1.fsdemousercenter.model.LoginPayload;
import com.xdorg1.fsdemousercenter.model.LogoutPayload;
import com.xdorg1.fsdemousercenter.model.User;
import com.xdorg1.fsdemousercenter.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;

@CrossOrigin
@RestController
public class UserCenterController {

    private static final Logger logger = LoggerFactory.getLogger(UserCenterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/api/getuserlist")
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

    @PostMapping("/api/login")
    public LoginPayload queryForLogin(String username, String password){
        logger.info("request content: username is " + username + ", password is " + password);
        return userService.queryForLogin(username, password);
    }

    @PostMapping("/api/logout")
    public LogoutPayload logout(String username, String token){
        logger.info("/api/logout called with username is " + username + ", token is " + token);
        return userService.logout(username, token);
    }

    @PostMapping("/api/adduser")
    public String addUser(User user){
        return userService.addUser(user);
    }

}
