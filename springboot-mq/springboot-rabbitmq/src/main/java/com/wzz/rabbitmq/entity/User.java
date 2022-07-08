package com.wzz.rabbitmq.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String uid;
    private String name;
    private String sex;
}
