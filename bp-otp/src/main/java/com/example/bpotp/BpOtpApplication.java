package com.example.bpotp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class BpOtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpOtpApplication.class, args);
	}




}
