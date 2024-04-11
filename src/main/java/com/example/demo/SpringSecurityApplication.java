package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entite.UserInfo;
import com.example.demo.service.UserInfoService;


@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

	
	@Autowired
	UserInfoService userInfoService; 
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		UserInfo userInfo=new UserInfo(1,"khalil","khalil@gmail.com", "ADMIN_ROLES,USER_ROLES", "azerty");
		userInfoService.addUser(userInfo);
		
	}

}
	

