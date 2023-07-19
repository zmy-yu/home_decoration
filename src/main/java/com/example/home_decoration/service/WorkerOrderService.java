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

}
