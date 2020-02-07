package com.xdorg1.fsdemousercenter.model;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    @Select("select * from usertable order by user_id desc")
    List<User> getUserList();

    @Insert("insert into usertable(username,userpassword) values(#{username},#{userpassword})")
    int addUser(User user);

    @Select("select user_id from usertable where username=#{username}")
    int getUserId(String username);

    @Select("select * from usertable where username=#{username}")
    User getUser(String username);

    @Select("select token from usertable where user_id=#{user_id}")
    String getUserToken(int user_id);

    @Insert("update usertable set token=#{user_token} where user_id=#{user_id}")
    int setUserToken(int user_id, String user_token);

}
