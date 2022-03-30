package com.wzz.redis.dao;

import com.wzz.redis.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @packageName com.wzz.redis.user
 * @fileName User.java
 * @author wzzws (wzz6b.com@gmail.com)
 * @createTime 2022/02/20 20:08:07
 * @lastModify 2022/02/20 20:08:07
 * @version 1.0.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User>{
    List<User> getAllUser();

    Integer modifyUser(User user);

    Integer delUser(User user);
}
