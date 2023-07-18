package com.example.home_decoration.service.impl;

import com.example.home_decoration.mapper.UserMapper;
import com.example.home_decoration.model.Result;
import com.example.home_decoration.pojo.User;
import com.example.home_decoration.service.UserService;
import com.example.home_decoration.tools.ClassExamine;
import com.example.home_decoration.tools.JwtUtil;
import io.micrometer.common.util.StringUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public Result<User> register(User user) {
        Result<User> result = new Result<>();
        // 先去数据库找用户名是否存在
        User getUser = userMapper.getByName(user.getU_username());
        if (getUser != null) {
            result.setResultFailed("该用户名已存在！");
            return result;
        }
        // 加密储存用户的密码
        user.setU_password(DigestUtils.md5Hex(user.getU_password()));
        // 存入数据库
        userMapper.add(user);
        // 返回成功消息
        result.setResultSuccess("注册成功", user);
        return result;
    }

    @Override
    public Result<User> login(User user) {
        Result<User> result = new Result<>();
        // 去数据库查找用户
        User getUser = userMapper.getByName(user.getU_username());
        if (getUser == null) {
            result.setResultFailed("用户名不存在");
            return result;
        }
        // 比对密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
        if (!getUser.getU_password().equals(DigestUtils.md5Hex(user.getU_password()))) {
            result.setResultFailed("用户名或者密码错误！");
            return result;
        }
        // 设定登录成功消息
        String token = JwtUtil.sign(getUser);
        result.setResultSuccess("登陆成功！", getUser, token);
        return result;
    }

    @Override
    public Result<User> update(User user) throws Exception {
        Result<User> result = new Result<>();
        // 去数据库查找用户
        User getUser = userMapper.getById(user.getU_id());
        if (getUser == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        // 检测传来的对象里面字段值是否为空，若是就用数据库里面的对象相应字段值补上
        if (!StringUtils.isEmpty(user.getU_password())) {
            // 加密储存
            user.setU_password(DigestUtils.md5Hex(user.getU_password()));
        }
        // 对象互补
        ClassExamine.objectOverlap(user, getUser);
        // 存入数据库
        userMapper.update(user);
        result.setResultSuccess("修改用户成功！", user);
        return result;
    }

    @Override
    public Result<User> delete(User user) {
        Result<User> result = new Result<>();
        // 去数据库查找用户
        User getUser = userMapper.getById(user.getU_id());
        if (getUser == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        // 删除数据
        userMapper.delete(user.getU_id());
        result.setResultSuccess("删除成功");
        return result;
    }

    @Override
    public Result<String> userUpload(String url, Integer u_id) {
        Result<String> result = new Result<>();
        userMapper.userUpload(url, u_id);
        result.setResultSuccess("上传成功！", url);
        return result;
    }

}
