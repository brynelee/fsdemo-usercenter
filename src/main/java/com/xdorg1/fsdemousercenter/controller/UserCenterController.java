package com.xdorg1.fsdemousercenter.controller;

import com.xdorg1.fsdemousercenter.model.*;
import com.xdorg1.fsdemousercenter.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RestController
//可以考虑使用前缀来简化每个方法的mapping，还没有尝试
//@RequestMapping(value = "/test")
public class UserCenterController {

    private static final Logger logger = LoggerFactory.getLogger(UserCenterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/usercenter/getuserlist")
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

    /**
     * 1. got input (redirect GET) from authsrv: http://fsdemo-usercenter:8081/usercenter/auth?code=KGZ0X3&state=test1
     * 2. send POST to authsrv: curl -v -X POST --user webclient2:time4@FUN http://fsdemo-authsrv:8084/oauth/token
     * -H "content-type: application/x-www-form-urlencoded"
     * -d "code=KGZ0X3&grant_type=authorization_code&redirect_uri=http://fsdemo-usercenter:8081/usercenter/auth&scope=read"
     * 3. got response from authsrv:
     * {"access_token":"3119b61e-a39a-4a09-a084-34b83891187a","token_type":"bearer","expires_in":7199,"scope":"read"}
     * 4. save token and respond to the client
     * @return
     */

    /*@GetMapping("/usercenter/auth")
    public UserToken authCodeRedirect(String code, String state){
        logger.info("request at /usercenter/auth with parameters: " + code + " and " + state);
        return userService.postForAuthToken(code, state);
    }*/



    @PostMapping("/usercenter/login")
    public LoginPayload queryForLogin(String username, String password){
        logger.info("request content: username is " + username + ", password is " + password);
        return userService.queryForLogin(username, password);
    }

    @PostMapping("/usercenter/logout")
    public LogoutPayload logout(String username, String token){
        logger.info("/usercenter/logout called with username is " + username + ", token is " + token);
        return userService.logout(username, token);
    }

    @PostMapping("/usercenter/adduser")
    public String addUser(User user){
        return userService.addUser(user);
    }

    @GetMapping("/usercenter/user")
    public UserEntity getUserInfo(String username){
        logger.info("/usercenter/user called with username: {}", username);
        return userService.getUserInfoByUsername(username);
    }

}
