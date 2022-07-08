package com.wzz.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzz.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User>
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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserById(String id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserByIds(String[] ids);
}
