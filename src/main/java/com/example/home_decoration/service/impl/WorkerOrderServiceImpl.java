package com.example.home_decoration.service.impl;

import com.example.home_decoration.mapper.WorkerMapper;
import com.example.home_decoration.mapper.WorkerOrderMapper;
import com.example.home_decoration.model.ConstructionQuery;
import com.example.home_decoration.model.Result;
import com.example.home_decoration.pojo.Worker;
import com.example.home_decoration.pojo.WorkerOrder;
import com.example.home_decoration.service.WorkerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkerOrderServiceImpl implements WorkerOrderService {

    @Autowired
    WorkerOrderMapper workerOrderMapper;

    @Autowired
    WorkerMapper workerMapper;

    /**
     * 修改订单信息
     *
     * @param constructionQuery 首页订单对象
     * @return 修改结果
     */
    @Override
    public int update(ConstructionQuery constructionQuery) {
        workerMapper.updateNameById(Integer.valueOf(constructionQuery.getW_id()), constructionQuery.getW_name());
        return workerOrderMapper.update(constructionQuery);
    }

    /**
     * 修改工人信息装修历史记录
     *
     * @param workerOrder 订单对象
     * @return
     */
    @Override
    public int updateHistoryOrder(WorkerOrder workerOrder) {
        return workerOrderMapper.updateHistoryOrder(workerOrder);
    }

    @Override
    public Result<WorkerOrder> add(WorkerOrder workerOrder) {
        Result<WorkerOrder> result = new Result<>();
        workerOrderMapper.add(workerOrder);
        result.setResultSuccess("添加成功！ ");
        return result;
    }

    @Override
    public Result<WorkerOrder> delete(Integer o_id) {
        Result<WorkerOrder> result = new Result<>();
        workerOrderMapper.delete(o_id);
        result.setResultSuccess("删除成功！");
        return result;
    }


}
