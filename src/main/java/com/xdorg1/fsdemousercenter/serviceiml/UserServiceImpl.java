package com.xdorg1.fsdemousercenter.serviceiml;

import com.xdorg1.fsdemousercenter.model.LoginPayload;
import com.xdorg1.fsdemousercenter.model.User;
import com.xdorg1.fsdemousercenter.model.UserMapper;
import com.xdorg1.fsdemousercenter.model.UserToken;
import com.xdorg1.fsdemousercenter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.ws.http.HTTPException;
import java.util.List;


@Repository
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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


    /*public User userLogin(String username, String password){

        User user = new User();
        user.setUsername(username);
        user.setUserToken("abcd");
        user.setUserId(userMapper.getUserId(username));
        return user;
    }*/

    @Override
    public String addUser(User user){

        try {
            int i = userMapper.addUser(user);
            return "添加成功" + i + "条数据";
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    //todo
   public LoginPayload queryForLogin(String username, String password){

        LoginPayload payload = new LoginPayload();
        payload.errorCode = LoginPayload.LOGIN_SUCCESS;
        payload.username = username;

        User user;

        logger.info("incoming login request: username is " + username + " and password is " + password);

        //check if use exist
        try {

            user = userMapper.getUser(username);

        }catch(Exception e){

            //todo: user not exist
            payload.errorCode = LoginPayload.USER_NOT_EXIST;
            payload.errorMessage = LoginPayload.MSG_USER_NOT_EXIST;
            return payload;
        }

        logger.info("the user information is " + user);

        //user exist, check password
       if (password.equals(user.getUserpassword())){

           //password is correct, check if token exist
           String userToken = user.getUserToken();
           if(userToken == null || userToken.length() <= 0){

               //password is correct and user token does not exist, need to create one
               UserToken newToken = new UserToken();
               payload.userToken = newToken.getUserToken();

               //save token to database back
               userMapper.setUserToken(user.getUserId(), newToken.getUserToken());

           }else{

               //password is correct and user token exists, check if it is expired
               UserToken existingToken = new UserToken(userToken);
               if (existingToken.isExpired()){

                   //token is expired, need to create a new one
                   UserToken newToken = new UserToken();
                   payload.userToken = newToken.getUserToken();

                   //save token to database back
                   userMapper.setUserToken(user.getUserId(), newToken.getUserToken());

               }else{
                   //existing token is still valid
                   payload.userToken = userToken;
               }

           }

       }else{
           //password is incorrect
            payload.errorCode = LoginPayload.PASSWORD_INCORRECT;
            payload.errorMessage = LoginPayload.MSG_PASSWORD_INCORRECT;
       }

       return payload;

    }

    //todo
    public Boolean userAuthentication(String token){
        return true;
    }
/*
    private Boolean ifTokenExist(int user_id){

        String token = userMapper.getUserToken(user_id);

        if (token.isEmpty()){
            return false;
        }else{
            return true;
        }
    }*/
}
