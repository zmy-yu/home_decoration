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
}
