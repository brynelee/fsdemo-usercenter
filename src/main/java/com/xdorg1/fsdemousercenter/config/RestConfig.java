package com.xdorg1.fsdemousercenter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    private static final String CLIENT_ID = "fsdemo-usercenter";
    private static final String CLIENT_CREDENTIAL = "time4@FUN";

    @Autowired
    RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate template = builder.basicAuthentication(CLIENT_ID, CLIENT_CREDENTIAL).build();
        return template;
    }
}
