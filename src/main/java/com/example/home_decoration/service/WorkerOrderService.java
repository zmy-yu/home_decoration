package com.example.home_decoration.service;

import com.example.home_decoration.model.ConstructionQuery;
import com.example.home_decoration.model.Result;
import com.example.home_decoration.pojo.Worker;
import com.example.home_decoration.pojo.WorkerOrder;
import org.springframework.stereotype.Service;

@Service
public interface WorkerOrderService {

    /**
     * 修改订单信息
     *
     * @param constructionQuery 订单对象
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
    Result<WorkerOrder> add(WorkerOrder workerOrder);

    /**
     * 删除工人装修历史
     *
     * @param o_id
     * @return
     */
    Result<WorkerOrder> delete(Integer o_id);
}
