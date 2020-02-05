package com.xdorg1.fsdemousercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//MapperScan的位置要准确
@MapperScan("com.xdorg1.fsdemousercenter.model")
public class FsdemoUsercenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsdemoUsercenterApplication.class, args);
	}

}
