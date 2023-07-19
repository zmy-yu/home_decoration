package com.example.home_decoration.mapper;

import com.example.home_decoration.model.ConstructionQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;


@Mapper
public interface WorkerOrderMapper {

    /**
     * 修改订单信息
     *
     * @param constructionQuery 首页订单对象
     * @return 修改成功记录条数
     */
    int update(ConstructionQuery constructionQuery);


}
