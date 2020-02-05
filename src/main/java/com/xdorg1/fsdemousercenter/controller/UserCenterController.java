package com.xdorg1.fsdemousercenter.controller;

import com.xdorg1.fsdemousercenter.model.User;
import com.xdorg1.fsdemousercenter.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
            logger.info("the user id " + user.user_id + " and user name is " + user.username);
        }
        return userService.getUserList();

    }

}
