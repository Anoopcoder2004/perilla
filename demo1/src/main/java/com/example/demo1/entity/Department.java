package com.example.demo1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Department{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<User> users;


    public Long getId(){
        return id;
    }
    public void SetId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= this.name;
    }

    public List<User> getUsers(){
        return users;
    }
    public void setUsers(List<User> user){
        this.users=user;
    }

    //getter and setter department


}