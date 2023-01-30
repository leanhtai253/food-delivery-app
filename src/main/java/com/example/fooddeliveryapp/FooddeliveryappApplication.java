package com.example.fooddeliveryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/welcome")
public class FooddeliveryappApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FooddeliveryappApplication.class, args);
    }
    @GetMapping("")
    public String helloTest() {
        return "Hello Spring Boot for Food Delivery App";
    }
}
