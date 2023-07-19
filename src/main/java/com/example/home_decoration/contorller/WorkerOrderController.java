package com.example.home_decoration.contorller;

import com.example.home_decoration.model.ConstructionQuery;
import com.example.home_decoration.model.Result;
import com.example.home_decoration.pojo.Worker;
import com.example.home_decoration.pojo.WorkerOrder;
import com.example.home_decoration.service.impl.WorkerOrderServiceImpl;
import com.example.home_decoration.tools.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/order")
@RestController
public class WorkerOrderController {

    @Autowired
    WorkerOrderServiceImpl workerOrderService;

    @PutMapping("update")
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
}
