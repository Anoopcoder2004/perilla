package com.example.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class Demo1Application {
    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
    @GetMapping("/user")
    public Map<String,String>getUser(){
        Map<String,String> user = new HashMap<>();
        user.put("name","Anoop");
        user.put("role","developer");
        return user;

    }

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}
}
