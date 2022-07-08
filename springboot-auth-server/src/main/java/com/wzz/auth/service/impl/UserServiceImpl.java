package com.wzz.auth.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzz.auth.entity.User;
import com.wzz.auth.mapper.UserMapper;
import com.wzz.auth.service.IUserService;
import com.wzz.util.JwtUtils;
import com.wzz.util.result.R;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2022-05-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper UserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 查询【请填写功能名称】
     *
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public User selectUserById(String id) {
        return UserMapper.selectUserById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param User 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<User> selectUserList(User User) {
        return UserMapper.selectUserList(User);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param User 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUser(User User) {
        return UserMapper.insertUser(User);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param User 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUser(User User) {
        return UserMapper.updateUser(User);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) {
        return UserMapper.deleteUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserById(String id) {
        return UserMapper.deleteUserById(id);
    }

    @Override
    public R registerUser(User user) {
//        if (ObjectUtils.isEmpty(user)) {
//            return new R().put("msg", "参数不能为空");
//        }
//        if (ObjectUtils.isEmpty(user.getUserName())) {
//            return new R().put("msg", "用户名称不能为空");
//        }
//        if (ObjectUtils.isEmpty(user.getUserNickName())) {
//            return new R().put("msg", "用户昵称不能为空");
//        }
//        if (ObjectUtils.isEmpty(user.getPassword())) {
//            return new R().put("msg", "密码不能为空");
//        }
//        if (ObjectUtils.isEmpty(user.getEmail())) {
//            return new R().put("msg", "邮箱不能为空");
//        }
//        if (ObjectUtils.isEmpty(user.getPhone())) {
//            return new R().put("msg", "电话不能为空");
//        }
//        if (!user.getPhone().matches("^[1]([3-9])[0-9]{9}$")){
//            return new R().put("msg", "手机格式不正确");
//        }

        Date date = new Date();
        SimpleDateFormat sdf_date_format = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf_date_format.format(date);
        String replace = UUID.randomUUID().toString().replace("-", "");

        user.setId(format + replace + date.getTime());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return new R().put("msg", this.save(user));
    }

    @Override
    public R login(User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userNickName", user.getNickName());
        User User = this.getOne(userQueryWrapper);

        if (User == null) {
            //todo 没有该用户
            return null;
        }

        if (!passwordEncoder.matches(user.getPassword(), User.getPassword())) {
            //todo 密码错误
            return null;
        }

        //生成token
        String jwtToken = JwtUtils.getJwtToken(user.getId(), user.getNickName());

        return new R().put("token", jwtToken);
    }
}
