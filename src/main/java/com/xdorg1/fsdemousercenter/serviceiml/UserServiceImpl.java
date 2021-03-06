package com.xdorg1.fsdemousercenter.serviceiml;

import com.xdorg1.fsdemousercenter.model.*;
import com.xdorg1.fsdemousercenter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Repository
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${fsdemo.oauth.client_id_fsdemo_frontend}")
    private String CLIENT_ID_FSDEMO_FRONTEND;
    @Value("${fsdemo.oauth.client_id_fsdemoapp}")
    private String CLIENT_ID_FSDEMOAPP;
    @Value("${fsdemo.oauth.client_credential}")
    private String CLIENT_CREDENTIAL;
    @Value("${fsdemo.oauth.tokenurl}")
    private String AUTHSRV_TOKEN_URL;
    @Value("${fsdemo.oauth.grant_type_code}")
    private String GRANT_TYPE_CODE;
    @Value("${fsdemo.oauth.redirect_uri}")
    private String REDIRECT_URI;


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
       if (password.equals(user.getPassword())){

           //password is correct, check if token exist
           String userToken = user.getUserToken();
           if(userToken == null || userToken.length() <= 0){

               //password is correct and user token does not exist, need to create one
               UserToken newToken = UserToken.getTokenFromAuthSrvByPasswordMode(username, password);
               payload.userToken = newToken.getUserToken();

               //save token to database back
               userMapper.setUserToken(user.getUserId(), newToken.getUserToken());

           }else{

               //password is correct and user token exists, check if it is expired
               UserToken existingToken = new UserToken(userToken);
               if (existingToken.isExpired()){

                   //token is expired, need to create a new one
                   UserToken newToken = UserToken.getTokenFromAuthSrvByPasswordMode(username, password);
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

    /**
     * send POST to authsrv: curl -v -X POST --user fsdemo-frontend:time4@FUN http://fsdemo-authsrv:8084/oauth/token
     *      * -H "content-type: application/x-www-form-urlencoded"
     *      * -d "code=KGZ0X3&grant_type=authorization_code&redirect_uri=http://fsdemo-usercenter:8081/usercenter/auth&scope=read"
     *      * 3. got response from authsrv:
     *      * {"access_token":"3119b61e-a39a-4a09-a084-34b83891187a","token_type":"bearer","expires_in":7199,"scope":"read"}
     * @param code
     * @param state
     * @return
     */

    public UserToken postForAuthToken(String code, String state){

        logger.info("UserServiceImpl postForAuthToken() with code: " + code + " and state: " + state);

        if (code == null || state == null){
            logger.error("UserServiceImpl postForAuthToken() got null input of code or state.");
            return null;
        }

        //designed by Xiaodong using state to differentiate from request originated from fsdemo-frontend or from fsdemoapp
        String client_id = (state.equals(CLIENT_ID_FSDEMO_FRONTEND))? CLIENT_ID_FSDEMO_FRONTEND : CLIENT_ID_FSDEMOAPP;
        String client_credential = CLIENT_CREDENTIAL;

        logger.info("UserServiceImpl postForAuthToken() with client_id " + client_id + " and client_credential " + client_credential);

        //set the basic auth
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.basicAuthentication(client_id, client_credential).build();

        //set the header
        //MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        //headers.add("content-type", "application/x-www-form-urlencoded");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //set the parameters in POST body
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("code", code);
        paramMap.add("grant_type", GRANT_TYPE_CODE);
        paramMap.add("redirect_uri", REDIRECT_URI);
        paramMap.add("scope", "all");

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(paramMap, headers);

        //ResponseEntity<String> response = restTemplate.postForObject(AUTHSRV_TOKEN_URL, requestEntity, String.class);
        UserToken userToken = restTemplate.postForObject(AUTHSRV_TOKEN_URL, requestEntity, UserToken.class);

        logger.info("UserServiceImpl postForAuthToken() got POST response from authsrv: " + userToken);

        return userToken;
    }

    public LogoutPayload logout(String username, String token){
        logger.info("incoming logout request with user: " + username + ", and token " + token);
        LogoutPayload payload = new LogoutPayload();
        payload.errorCode = LogoutPayload.TOKEN_ILLEGAL;

        if (userAuthentication(username, token)){
            logger.info("logout request token checking passed, username " + username + ", will remove token from the database.");

/*            try {
                logoutAuthsrv(username, token);
            }catch(Exception e){
                e.printStackTrace();
            }*/

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

    private void logoutAuthsrv(String username, String token){

        logger.info("sending POST /logout to fsdemo_authsrv for user: {}", username);

        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("username", username);
        paramMap.add("access_token", token);

        //RestTemplateBuilder builder = new RestTemplateBuilder();

        //restTemplate = builder.basicAuthentication(CLIENT_ID, CLIENT_CREDENTIAL).build();

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.postForObject("http://fsdemo-authsrv:8084/logout", paramMap, String.class);

        logger.info("Got POST response from fsdemo-authsrv /logout: " + response);

        /*
        ObjectMapper mapper = new ObjectMapper();
        UserToken userToken = null;

        try {
            userToken = mapper.readValue(response, UserToken.class);
            //复杂情形例如List或者Array等，可以使用下面的方法来进行反实例化
            //userToken = mapper.readValue(response, new TypeReference<UserToken>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return userToken;
        */
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

    public UserEntity getUserInfoByUsername(String username){
        logger.info("getUserInfoByUsername called with username: {}", username);
        UserEntity userEntity = userMapper.getUserEntity(username);
        logger.info("getUserInfoByUsername called and got userEntity: {}", userEntity);
        return userEntity;
    }

    public void setUserToken(String username, String token){

        logger.info("setUserToken of user {} with token: {}", username, token);

        userMapper.setUserToken(userMapper.getUserId(username), token);

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
