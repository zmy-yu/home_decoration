package com.example.home_decoration.mapper;

import com.example.home_decoration.model.ConstructionQuery;
import com.example.home_decoration.pojo.WorkerOrder;
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

    /**
     * 修改工人装修历史
     *
     * @param workerOrder
     * @return
     */
    int updateHistoryOrder(WorkerOrder workerOrder);

    /**
     * 添加工人装修历史
     *
     * @param workerOrder
     * @return
     */
    int add(WorkerOrder workerOrder);

    /**
     * 删除工人装修历史
     *
     * @param o_id
     * @return
     */
    int delete(Integer o_id);
}
