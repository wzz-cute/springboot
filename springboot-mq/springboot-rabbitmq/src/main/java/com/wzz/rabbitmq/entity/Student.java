package com.wzz.rabbitmq.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private Integer sid;
    private String name;
    private String sex;
}
