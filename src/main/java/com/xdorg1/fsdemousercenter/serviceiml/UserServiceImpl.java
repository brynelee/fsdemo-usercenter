package com.xdorg1.fsdemousercenter.serviceiml;

import com.xdorg1.fsdemousercenter.model.User;
import com.xdorg1.fsdemousercenter.model.UserMapper;
import com.xdorg1.fsdemousercenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getUserList() {

        try {
            List<User> users = userMapper.getUserList();

            return  users;
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
