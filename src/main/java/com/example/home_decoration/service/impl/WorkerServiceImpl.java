package com.example.home_decoration.service.impl;

import com.example.home_decoration.mapper.UserMapper;
import com.example.home_decoration.mapper.WorkerMapper;
import com.example.home_decoration.model.ConstructionQuery;
import com.example.home_decoration.model.QueryHome;
import com.example.home_decoration.model.WorkerQuery;
import com.example.home_decoration.model.Result;
import com.example.home_decoration.pojo.User;
import com.example.home_decoration.pojo.Worker;
import com.example.home_decoration.pojo.WorkerOrder;
import com.example.home_decoration.service.WorkerService;
import com.example.home_decoration.tools.ClassExamine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    WorkerMapper workerMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public Result<Worker> add(Worker worker, User user) {
        Result<Worker> result = new Result<>();
        worker.setU_id(user.getU_id());
        // 新增工人
        workerMapper.add(worker);

        // 获取跟新后的工人信息
//        Worker newWorker = workerMapper.getByU_Id(user.getU_id());
        // 更新用户角色
//        userMapper.updateRole(newWorker.getW_id(), user.getU_id(), 1);

        // 获取更新后的用户信息
//        User getUser = userMapper.getById(user.getU_id());
//        List list = new ArrayList<>();
//        list.add(worker);
//        list.add(getUser);
        result.setResultSuccess("新增成功！", worker);
        return result;
    }

    @Override
    public Result<Worker> update(Worker worker) throws Exception {
        Result<Worker> result = new Result<>();
        // 去数据库查找用户原信息
        Worker getWorker = workerMapper.getById(worker.getW_id());
        // 对象互补
        ClassExamine.objectOverlap(worker, getWorker);
        // 修改工人信息
        workerMapper.update(worker);
        result.setResultSuccess("修改用户成功！", worker);
        return result;
    }

    @Override
    public Result<Worker> getById(Integer w_id) {
        Result<Worker> result = new Result<>();
        result.setResultSuccess("获取用户信息成功", workerMapper.getById(w_id));
        return result;
    }

    /**
     * 获取工人信息
     * @param workerQuery
     * @return
     */
    @Override
    public Result<List<QueryHome>> getWorkers(WorkerQuery workerQuery) {
        Result<List<QueryHome>> result = new Result<>();
        result.setResultSuccess("1", workerMapper.getWorkers(workerQuery));
        return result;
    }

    /**
     * 获取首页施工状态
     * @return
     */
    @Override
    public Result<List<ConstructionQuery>> getConstructionWorker() {
        Result<List<ConstructionQuery>> result = new Result<>();
        result.setResultSuccess("1", workerMapper.getConstructionWorker());
        return result;
    }

    @Override
    public Result<Worker> delete(Worker worker, Integer u_id) {
        Result<Worker> result = new Result<>();
        // 去数据库查找用户
        Worker getWorker = workerMapper.getById(worker.getW_id());
        if (getWorker == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        // 删除数据
        workerMapper.delete(worker.getW_id());
        userMapper.updateRole(0, u_id, 0);
        result.setResultSuccess("删除成功", getWorker);
        return result;
    }

    @Override
    public List<Integer> getW_idByU_id(Integer u_id) {
        return workerMapper.getW_idByU_id(u_id);
    }

    @Override
    public Result<String> workerUpload(String url, Integer w_id) {
        Result<String> result = new Result<>();
        workerMapper.workerUpload(url, w_id);
        result.setResultSuccess("上传成功！", url);
        return result;
    }

    @Override
    public Result<Map<String, Object>> getWorkerPrices(Integer w_id) {
        Result<Map<String, Object>> result = new Result<>();
        result.setResultSuccess("1", workerMapper.getWorkerPrices(w_id));
        return result;
    }

    @Override
    public Result<List<WorkerOrder>> getHistoryOrder(Integer w_id) {
        Result<List<WorkerOrder>> result = new Result<>();
        result.setResultSuccess("1", workerMapper.getHistoryOrder(w_id));
        return result;
    }
}
