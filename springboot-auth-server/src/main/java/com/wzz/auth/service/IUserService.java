package com.wzz.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzz.auth.entity.User;
import com.wzz.util.result.R;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2022-05-28
 */
public interface IUserService  extends IService<User>
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public User selectUserById(String id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param User 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<User> selectUserList(User User);

    /**
     * 新增【请填写功能名称】
     * 
     * @param User 【请填写功能名称】
     * @return 结果
     */
    public int insertUser(User User);

    /**
     * 修改【请填写功能名称】
     * 
     * @param User 【请填写功能名称】
     * @return 结果
     */
    public int updateUser(User User);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteUserByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserById(String id);

    R registerUser(User user);

    R login(User user);
}
