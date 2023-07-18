package com.example.home_decoration.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PictureMapper {

    /**
     * 添加项目图片
     * @param url
     * @param o_id
     * @return
     */
    int projectUpload(String url, Integer o_id);

    /**
     * 获取项目要图片
     * @param o_id
     * @return
     */
    String getProjectUrl(Integer o_id);
}
