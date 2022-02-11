package com.wzz.mongo.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document("user")
public class User {

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("password")
    private String password;

    @Field("address")
    private String address;

    @Field("create_time")
    private Date createTime;

    @Field("last_update_time")
    private Date lastUpdateTime;

    // ... ignore getter and setter methods

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
