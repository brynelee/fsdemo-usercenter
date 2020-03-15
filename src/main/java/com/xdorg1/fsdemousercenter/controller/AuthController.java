package com.xdorg1.fsdemousercenter.controller;

import com.xdorg1.fsdemousercenter.model.UserToken;
import com.xdorg1.fsdemousercenter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/usercenter/auth")
    public String authCodeRedirect(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletRequest request){
        logger.info("request at /usercenter/auth with parameters: " + code + " and " + state);
        UserToken userToken =  userService.postForAuthToken(code, state);
        request.setAttribute("message", userToken.getUserToken());
        return "index";
    }
}
