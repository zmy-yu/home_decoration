package com.example.home_decoration.service;

import com.example.home_decoration.model.ConstructionQuery;
import com.example.home_decoration.model.QueryHome;
import com.example.home_decoration.model.WorkerQuery;
import com.example.home_decoration.model.Result;
import com.example.home_decoration.pojo.User;
import com.example.home_decoration.pojo.Worker;
import com.example.home_decoration.pojo.WorkerOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface WorkerService {

    /**
     * 新增工人
     *
     * @param worker 工人对象
     * @return 新增成功记录条数
     */
    Result<Worker> add(Worker worker, User user);

    /**
     * 更新工人信息
     *
     * @param worker 工人对象
     * @return 修改结果
     */
    Result<Worker> update(Worker worker) throws Exception;

    Result<Worker> getById(Integer w_id);

    /**
     * 获取工人信息
     *
     * @return 工人信息集
     */
    Result<List<QueryHome>> getWorkers(WorkerQuery workerQuery);

    Result<List<ConstructionQuery>> getConstructionWorker();

    /**
     * 删除工人信息
     * @param worker 工人对象
     * @return 删除结果
     */
    Result<Worker> delete(Worker worker, Integer u_id);

    /**
     * 判断用户下有哪些工人id
     * @param u_id
     * @return
     */
    List<Integer> getW_idByU_id(Integer u_id);

    /**
     * 工人头像修改
     * @param url
     * @param w_id
     * @return
     */
    Result<String> workerUpload(String url, Integer w_id);

    /**
     * 获取工人价格和历史价格
     *
     * @param w_id
     * @return
     */
    Result<Map<String, Object>> getWorkerPrices(Integer w_id);

    /**
     * 获取工人装修历史
     *
     * @param w_id
     * @return
     */
    Result<List<WorkerOrder>> getHistoryOrder(Integer w_id);
}
