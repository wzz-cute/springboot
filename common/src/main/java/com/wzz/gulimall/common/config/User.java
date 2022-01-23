package com.wzz.gulimall.common.config;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private  String  name;
    private String spec;
    private List<String> fff;
    public User(String name,String spec,List<String> fff){
        this.name=name;
        this.spec=spec;
        this.fff=fff;
    }
}
