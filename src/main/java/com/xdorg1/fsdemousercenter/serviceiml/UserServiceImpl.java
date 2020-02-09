package com.xdorg1.fsdemousercenter.serviceiml;

import com.xdorg1.fsdemousercenter.model.*;
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

        User user = null;

        logger.info("incoming login request: username is " + username + " and password is " + password);

        //check if use exist
        /*try {

            user = userMapper.getUser(username);

        }catch(Exception e){

            payload.errorCode = LoginPayload.USER_NOT_EXIST;
            payload.errorMessage = LoginPayload.MSG_USER_NOT_EXIST;
            return payload;
        }*/

        user = userMapper.getUser(username);
        if(user == null){
            logger.info("the user " + username + " was not found in the database, will return error to the client.");
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

    public LogoutPayload logout(String username, String token){
        logger.info("incoming logout request with user: " + username + ", and token " + token);
        LogoutPayload payload = new LogoutPayload();
        payload.errorCode = LogoutPayload.TOKEN_ILLEGAL;

        if (userAuthentication(username, token)){
            logger.info("logout request token checking passed, username " + username + ", will remove token from the database.");
            //remove the token
            userMapper.removeUserToken(username);
            //return success code
            payload.errorCode = LogoutPayload.LOGOUT_SUCCESS;

        }else{

            logger.info("logout request token checking failed, username " + username + ", will remove token from the database but return TOKEN_ILLEGAL payload information.");
            userMapper.removeUserToken(username);

        }

        return payload;

    }

    // todo: add token verification logic
    public Boolean userAuthentication(String username, String token){

        String tokenInDB = userMapper.getUserToken(username);

        if (tokenInDB == null){
            logger.info("Didn't got token for user " + username);
            return false;
        }

        return tokenInDB.equals(token);
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
