package com.xdorg1.fsdemousercenter.service;

import com.xdorg1.fsdemousercenter.model.LoginPayload;
import com.xdorg1.fsdemousercenter.model.LogoutPayload;
import com.xdorg1.fsdemousercenter.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getUserList();

    String addUser(User user);

    //if success, return the User with user_id and user_token information
    LoginPayload queryForLogin(String username, String password);

    LogoutPayload logout(String username, String token);

    Boolean userAuthentication(String username, String token);
}
