package com.example.home_decoration.contorller;

import com.example.home_decoration.model.Result;
import com.example.home_decoration.pojo.User;
import com.example.home_decoration.service.impl.UserServiceImpl;
import com.example.home_decoration.tools.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * session的字段名
     */
    public static final String SESSION_NAME = "userinfo";
    @Autowired
    UserServiceImpl userService;

    /**
     * 用户注册
     *
     * @param user    传入注册用户信息
     * @param errors  Validation的校验错误存放对象
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody @Valid User user, BindingResult errors) {
        Result<User> result = new Result<>();
        // 如果校验有错，返回注册失败以及错误信息
        if (errors.hasErrors()) {
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return  result;
        }
        // 调用注册服务
        result = userService.register(user);
        return result;
    }

    /**
     * 用户登录
     *
     * @param user    传入登录用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody @Valid User user, BindingResult errors, HttpServletRequest request) {
        Result<User> result = new Result<>();
        // 如果校验有错，返回登录失败以及错误信息
        if (errors.hasErrors()) {
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return  result;
        }
        // 调用登录服务
        result = userService.login(user);
        return result;
    }

    /**
     * 用户信息修改
     *
     * @param user    修改后用户信息对象
     * @param request 请求对象，用于操作session
     * @return 修改结果
     */
    @PutMapping("/update")
    public Result<User> update(@RequestBody User user, HttpServletRequest request) throws Exception {
        Result<User> result = new Result<>();
        Integer u_id = JwtUtil.getU_id(request);
        if (u_id != user.getU_id().intValue()) {
            result.setResultFailed("当前登录用户和被修改用户不一致，终止！");
            return result;
        }
        result = userService.update(user);
        return result;
    }

    /**
     * 用户注销
     *
     * @param user 用户对象
     * @return 注销结果
     */
    @DeleteMapping("/delete")
    public Result<User> delete(@RequestBody User user, HttpServletRequest request) {
        Result<User> result = new Result<>();
        Integer u_id = JwtUtil.getU_id(request);
        if (u_id != user.getU_id().intValue()) {
            result.setResultFailed("当前登录用户和被修改用户不一致，终止！");
            return result;
        }
        result = userService.delete(user);
        return result;
    }

    /**
     * 修改用户头像
     *
     * @param file 图片
     * @param request 请求数据
     * @return
     */
    @PostMapping("/userupload")
    public Result<String> userUpload(MultipartFile file, HttpServletRequest request) {
        Result<String> result = new Result<>();
        if (file.isEmpty()) {
            result.setResultFailed("上传失败");
            return result;
        }
        //获取文件名和后缀
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = System.currentTimeMillis() + "-" + fileName;
        //上传路径
        String filePath = "/home/resources/picture/user/";
        String path = filePath + newFileName;
        File newFile = new File(path);
        //如果文件夹不存在，创建文件夹路径
        if (!newFile.getParentFile().exists()) {
            newFile.mkdirs();
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = "www.zhikao168.com:8083/images/user/" + newFileName;
        Integer u_id = JwtUtil.getU_id(request);
        result = userService.userUpload(url, u_id);
        return result;
    }
}
