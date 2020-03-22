package com.xdorg1.fsdemousercenter.model;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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


    /**
     *     private String username;
     *     private String password;
     *     private Integer user_id;
     *     private String mobile;
     *     private String email;
     *     private Integer status;
     *     private String personal_id;
     * @param username
     * @return
     */

    @Select("select username, password, user_id, mobile, email, status, personal_id from usertable where username=#{username}")
    UserEntity getUserEntity(String username);

    @Select("select token from usertable where username=#{username}")
    String getUserToken(String username);

    @Update("update usertable set token=null where username=#{username}")
    int removeUserToken(String username);

    @Insert("update usertable set token=#{user_token} where user_id=#{user_id}")
    int setUserToken(int user_id, String user_token);

}
