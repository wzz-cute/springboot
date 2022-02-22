package com.wzz.redis.service;

import java.util.List;

import com.wzz.gulimall.common.utils.R;
import com.wzz.redis.entity.User;

/**
 * @author wzzws (wzz6b.com@gmail.com)
 * @version 1.0.0
 * @packageName com.wzz.redis.user
 * @fileName User.java
 * @createTime 2022/02/20 20:08:07
 * @lastModify 2022/02/20 20:08:07
 */
public interface UserService {
    public int add(User entity);

    public int del(long id);

    public int modify(User entity);

    public List<User> findAll(User entity);

    public User findById(long id);

    List<User> getAllUser();
}
