package com.wzz.redis.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wzzws (wzz6b.com@gmail.com)
 * @version 1.0.0
 * @packageName com.wzz.redis.user
 * @fileName User.java
 * @createTime 2022/02/20 20:08:07
 * @lastModify 2022/02/20 20:08:07
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName
public class User {

    private Long uid; //
    private Long pid; //
    private String uname; //
    private String sex; //
    private String nickName; //

    //批量操作id
    private List<Long> uids;

}
