package com.example.home_decoration.mapper;

import com.example.home_decoration.model.ConstructionQuery;
import com.example.home_decoration.model.QueryHome;
import com.example.home_decoration.model.WorkerQuery;
import com.example.home_decoration.pojo.Worker;
import com.example.home_decoration.pojo.WorkerOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WorkerMapper {

    /**
     * 新增工人信息
     *
     * @param worker 工人对象
     * @return 新增成功记录条数
     */
    int add(Worker worker);

    /**
     * 修改工人信息
     *
     * @param worker 工人对象
     * @return 修改成功记录条数
     */
    int update(Worker worker);

    /**
     * 根据id获取工人信息
     *
     * @param w_id 用户id
     * @return 工人对象
     */
    Worker getById(Integer w_id);

    Worker getByU_Id(Integer u_id);

    /**
     * 获取工人信息
     * @return 工人对象
     */
    List<QueryHome> getWorkers(WorkerQuery workerQuery);

    /**
     * 根据id删除工人信息
     *
     * @param w_id 用户id
     * @return 删除成功记录条数
     */
    int delete(Integer w_id);

    /**
     * @return 获取首页施工状态
     */
    List<ConstructionQuery> getConstructionWorker();

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
    int workerUpload(String url, Integer w_id);

    /**
     * 获取工人价格和历史价格
     *
     * @param w_id
     * @return
     */
    Map<String, Object> getWorkerPrices(Integer w_id);

    /**
     * 获取工人装修历史
     * @param w_id
     * @return
     */
    List<WorkerOrder> getHistoryOrder(Integer w_id);
}
