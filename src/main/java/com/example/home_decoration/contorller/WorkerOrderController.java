package com.example.home_decoration.contorller;

import com.example.home_decoration.model.ConstructionQuery;
import com.example.home_decoration.model.Result;
import com.example.home_decoration.pojo.Worker;
import com.example.home_decoration.pojo.WorkerOrder;
import com.example.home_decoration.service.impl.WorkerOrderServiceImpl;
import com.example.home_decoration.tools.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class WorkerOrderController {

    @Autowired
    WorkerOrderServiceImpl workerOrderService;

    @PutMapping("/update")
    public Result<ConstructionQuery[]> update(@RequestBody ConstructionQuery[] constructionQueries, HttpServletRequest request) {
        Result result = new Result<>();
        Integer u_role = JwtUtil.getU_role(request);
        if (u_role != 9) {
            result.setResultFailed("没有权限修改，请联系管理员！");
            return result;
        }
        for (int i = 0; i < constructionQueries.length; i++) {
            workerOrderService.update(constructionQueries[i]);
        }
        result.setResultSuccess("修改成功！");
        return result;
    }

    /**
     * 修改工人装修历史
     *
     * @param workerOrder
     * @param request
     * @return
     */
    @PutMapping("/updateHistoryOrder")
    public Result<WorkerOrder> updateHistoryOrder(@RequestBody WorkerOrder[] workerOrder, HttpServletRequest request) {
        Result result = new Result<>();
        Integer u_role = JwtUtil.getU_role(request);
        if (u_role != 9) {
            result.setResultFailed("没有权限修改，请联系管理员！");
            return result;
        }
        for (int i = 0; i < workerOrder.length; i++) {
            workerOrderService.updateHistoryOrder(workerOrder[i]);
        }
        result.setResultSuccess("修改成功！");
        return result;
    }

    /**
     * 添加工人装修历史
     *
     * @param workerOrder
     * @param request
     * @return
     */
    @PostMapping("/add")
    public Result<WorkerOrder> add(@RequestBody WorkerOrder workerOrder, HttpServletRequest request) {
        Result<WorkerOrder> result = new Result<>();
        Integer u_role = JwtUtil.getU_role(request);
        if (u_role != 9) {
            result.setResultFailed("没有权限修改，请联系管理员！");
            return result;
        }
        return workerOrderService.add(workerOrder);
    }

    @DeleteMapping("/delete")
    public Result<WorkerOrder> delete(@RequestParam("o_id") Integer o_id, HttpServletRequest request) {
        Result<WorkerOrder> result = new Result<>();
        Integer u_role = JwtUtil.getU_role(request);
        if (u_role != 9) {
            result.setResultFailed("没有权限修改，请联系管理员！");
            return result;
        }
        return workerOrderService.delete(o_id);
    }
}
