package com.example.demo1.controller;

import com.example.demo1.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public  class DemoController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/user")
    public User getUser(){
        return new User("Anoop","Developer");
    }
    @PostMapping("/add-user")
    public User addUser(@RequestBody User user){
        return user;
    }



}