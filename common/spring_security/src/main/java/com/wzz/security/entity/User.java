package com.wzz.security.entity;



import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户实体类
 * </p>
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String nickName;

    private String salt;

    private String token;

}



