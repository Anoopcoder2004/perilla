package com.example.demo1.controller;

import com.example.demo1.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<User> addUser(@RequestBody List<User> users){
        return users;
    }



}