package com.xdorg1.fsdemousercenter.service;

import com.xdorg1.fsdemousercenter.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getUserList();

    String addUser(User user);

}
