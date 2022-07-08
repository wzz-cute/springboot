package com.wzz.base.listener.service;

import com.wzz.base.listener.entity.User;
import com.wzz.util.result.R;

public interface UserService {
    R updateUserInfoBySpringbootListener(User user);

    R updateUserInfoByMyListener(User user);
}
