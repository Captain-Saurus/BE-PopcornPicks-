package com.example.popcornpicks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PopcornPicksApplication {

    public static void main(String[] args) {
        SpringApplication.run(PopcornPicksApplication.class, args);
    }

}
