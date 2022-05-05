package com.wzz.offer.pdf;

import lombok.Data;

@Data
public class Student {
    private Integer id;
    private String name;
    private String nickName;

    public Student(Integer id,
                   String name,
                   String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;

    }
}
