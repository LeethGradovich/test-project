package com.example.bpnotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BpNotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(BpNotificationApplication.class, args);
    }
}
