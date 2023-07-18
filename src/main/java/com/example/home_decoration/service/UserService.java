package com.example.home_decoration.service;

import com.example.home_decoration.model.Result;
import com.example.home_decoration.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * 用户注册
     *
     * @param user 用户对象
     * @return 注册结果
     */
    Result<User> register(User user);

    /**
     * 用户登录
     *
     * @param user 用户对象
     * @return 登录结果
     */
    Result<User> login(User user);

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return 修改结果
     */
    Result<User> update(User user) throws Exception;

    /**
     * 删除用户信息
     * @param user 用户对象
     * @return 删除结果
     */
    Result<User> delete(User user);

    Result<String> userUpload(String url, Integer u_id);
}
