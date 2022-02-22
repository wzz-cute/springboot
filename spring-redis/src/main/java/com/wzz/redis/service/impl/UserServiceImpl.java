package com.wzz.redis.service.impl;

import java.util.List;

import com.wzz.redis.entity.User;
import com.wzz.redis.dao.UserMapper;
import com.wzz.redis.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author wzzWanSui (wzz6b.com@gmail.com)
 * @version 1.0.0
 * @packageName com.wzz.redis.user
 * @fileName User.java
 * @createTime 2022/02/20 20:08:07
 * @lastModify 2022/02/20 20:08:07
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAll(User entity) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public User findById(long id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("UserId", id);
        return (User) userMapper.selectList(queryWrapper);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public int add(User entity) {
        try {
            return userMapper.insert(entity);
        } catch (Exception e) {
            LOGGER.error("The class UserServiceImpl's function [save(User)]  occurred errors.", e);
            throw e;
        }
    }

    @Override
    public int modify(User entity) {
        try {
            return userMapper.update(entity, null);
        } catch (Exception e) {
            LOGGER.error("The class UserServiceImpl's function [update(User)]  occurred errors.", e);
            throw e;
        }
    }

    @Override
    public int del(long id) {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
            queryWrapper.eq("UserId", id);
            return userMapper.delete(queryWrapper);
        } catch (Exception e) {
            LOGGER.error("The class UserServiceImpl's function [delete(Long)]  occurred errors.", e);
            throw e;
        }
    }

}
