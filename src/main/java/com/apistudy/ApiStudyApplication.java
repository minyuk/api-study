package com.apistudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiStudyApplication.class, args);
	}


	//카카오 api
	//key : 69b67b782df33be906a37fe87a9b5d26
	//로그인 요청 : http://localhost:8080/auth/kakao/callback
}
