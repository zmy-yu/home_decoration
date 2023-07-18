package com.example.home_decoration.mapper;

import com.example.home_decoration.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return 新增成功记录条数
     */
    int add(User user);

    /**
     * 修改用户
     *
     * @param user 用户对象
     * @return 修改成功记录条数
     */
    int update(User user);

    /**
     * 根据id获取用户
     *
     * @param u_id 用户id
     * @return 用户对象
     */
    User getById(Integer u_id);

    /**
     * 根据用户名获取用户
     *
     * @param u_name 用户名
     * @return 用户对象
     */
    User getByName(String u_name);

    /**
     * 根据id删除用户
     *
     * @param u_id 用户id
     * @return 删除成功记录条数
     */
    int delete(Integer u_id);

    /**
     * 更新用户角色
     *
     * @param w_id
     * @param u_id
     * @param u_role
     * @return
     */
    int updateRole(Integer w_id, Integer u_id, Integer u_role);

    /**
     * 修改用户头像
     * @param url
     * @param u_id
     * @return
     */
    int userUpload(String url, Integer u_id);
}
