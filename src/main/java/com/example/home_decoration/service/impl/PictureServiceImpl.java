package com.example.home_decoration.service.impl;

import com.example.home_decoration.mapper.PictureMapper;
import com.example.home_decoration.model.Result;
import com.example.home_decoration.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureMapper pictureMapper;
    @Override
    public Result<String> projectUpload(String url, Integer o_id) {
        Result<String> result = new Result<>();
        pictureMapper.projectUpload(url, o_id);
        result.setResultSuccess("上传成功！", url);
        return result;
    }
    @Override
    public Result<String> getProjectUrl(Integer o_id) {
        Result<String> result = new Result<>();
        String url = pictureMapper.getProjectUrl(o_id);
        result.setResultSuccess("获取url成功!",url);
        return result;
    }
}
