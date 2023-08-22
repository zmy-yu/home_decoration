package com.example.home_decoration.contorller;

import com.example.home_decoration.model.ConstructionQuery;
import com.example.home_decoration.model.QueryHome;
import com.example.home_decoration.model.WorkerQuery;
import com.example.home_decoration.model.Result;
import com.example.home_decoration.pojo.User;
import com.example.home_decoration.pojo.Worker;
import com.example.home_decoration.pojo.WorkerOrder;
import com.example.home_decoration.service.impl.WorkerServiceImpl;
import com.example.home_decoration.tools.GetAge;
import com.example.home_decoration.tools.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/worker")
@RestController
public class WorkerController {

    @Autowired
    WorkerServiceImpl workerService;

    @GetMapping("")
    public Result<List<QueryHome>> getWorkers(@RequestParam(value = "w_typeWork", required = false) String w_typeWork,
                                              @RequestParam(value = "w_habitualResidenceCity", required = false) String w_habitualResidenceCity,
                                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        WorkerQuery workerQuery = new WorkerQuery();
        Integer pageNum = (page - 1) * pageSize;
        workerQuery.setW_typeWork(w_typeWork);
        workerQuery.setPageNum(pageNum);
        workerQuery.setPageSize(pageSize);
        workerQuery.setW_habitualResidenceCity(w_habitualResidenceCity);
        return workerService.getWorkers(workerQuery);
    }

    @GetMapping("/construction")
    public Result<List<ConstructionQuery>> getConstructionWorker() {
        return workerService.getConstructionWorker();
    }

    /**
     * 根据工人id查询工人信息
     *
     * @param w_id 工人id
     * @return
     */
    @GetMapping("/id")
    public Result<Worker> getById(@RequestParam("w_id") Integer w_id) {
        return workerService.getById(w_id);
    }

    /**
     * 添加工人信息
     *
     * @param worker 工人对象
     * @param errors 校验
     * @param request 前端请求
     * @return
     * @throws ParseException
     */
    @PostMapping("/add")
    public Result<Worker> add(@RequestBody @Valid Worker worker, BindingResult errors, HttpServletRequest request) throws ParseException {
        Result<Worker> result = new Result<>();
        // 如果校验有错，返回登录失败以及错误信息
        if (errors.hasErrors()) {
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        Integer u_id = JwtUtil.getU_id(request);
        User user_1 = new User();
        user_1.setU_id(u_id);
        //计算工人年龄
        if ( worker.getW_birthday() != null) {
            Date birth = GetAge.parse(worker.getW_birthday());
            worker.setW_age(GetAge.getAgeByBirth(birth));
        }
        //工人历史价格
        worker.setW_historyPrice(worker.getW_price());
        // 调用新增方法
        result = workerService.add(worker, user_1);
        return result;
    }

    /**
     * 修改工人信息
     *
     * @param worker 工人对象
     * @param request 前端请求
     * @return
     * @throws Exception
     */
    @PutMapping("update")
    public Result<Worker> update(@RequestBody @Valid Worker worker, HttpServletRequest request) throws Exception {
        Result<Worker> result = new Result<>();
        Integer u_id = JwtUtil.getU_id(request);
        Integer u_role = JwtUtil.getU_role(request);
        //计算工人年龄
        if ( worker.getW_birthday() != null) {
            Date birth = GetAge.parse(worker.getW_birthday());
            worker.setW_age(GetAge.getAgeByBirth(birth));
        }
        //工人历史价格
        worker.setW_historyPrice(worker.getW_price());
        if (u_role == 9) {
            result = workerService.update(worker);
            return result;
        }
        //判断用户下有哪些工人id
        List<Integer> w_idList = workerService.getW_idByU_id(u_id);
        for (Integer w_id : w_idList) {
            if (w_id == worker.getW_id()) {
                result = workerService.update(worker);
                return result;
            }
        }
        result.setResultFailed("当前登录用户和被修改用户不一致，终止！");
        return result;
    }

    /**
     * 用户注销
     *
     * @param worker 用户对象
     * @return 注销结果
     */
    @DeleteMapping("/delete")
    public Result<Worker> delete(@RequestBody Worker worker, HttpServletRequest request) {
        Result<Worker> result = new Result<>();
        Integer u_id = JwtUtil.getU_id(request);
//        Integer w_id = JwtUtil.getW_id(request);
        Integer u_role = JwtUtil.getU_role(request);
        if (u_role == 9) {
            result = workerService.delete(worker, u_id);
            return result;
        }
//        if (w_id != worker.getW_id().intValue()) {
//            result.setResultFailed("当前登录用户和被修改用户不一致，终止！");
//            return result;
//        }
        worker.setU_id(u_id);
        return workerService.delete(worker, u_id);
    }

    /**
     * 修改工人头像
     *
     * @param file 图片文件
     * @param w_id 工人id
     * @return
     */
    @PostMapping("/workerupload")
    public Result<String> workerUpload(MultipartFile file, @RequestParam("w_id") Integer w_id) {
        Result<String> result = new Result<>();
        if (file.isEmpty()) {
            result.setResultFailed("上传失败");
            return result;
        }
        //获取文件名和后缀
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = System.currentTimeMillis() + "-" + fileName;
        //上传路径
        String filePath = "/home/resources/picture/worker/";
        String path = filePath + newFileName;
        File newFile = new File(path);
        //如果文件夹不存在，创建文件夹路径
        if (!newFile.getParentFile().exists()) {
            newFile.mkdirs();
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String url = "www.zhikao168.com:7090/images/worker/" + newFileName;
        result = workerService.workerUpload(url, w_id);
        return result;
    }

    /**
     * 获取工人价格和历史价格
     *
     * @param w_id 工人id
     * @return
     */
    @GetMapping("/prices")
    public Result<Map<String, Object>> getWorkerPrices(@RequestParam("w_id") Integer w_id) {
        return workerService.getWorkerPrices(w_id);
    }

    /**
     * 获取工人装修历史
     *
     * @param w_id
     * @return
     */
    @GetMapping("/historyorder")
    public Result<List<WorkerOrder>> getHistoryOrder(@RequestParam("w_id") Integer w_id) {
        return workerService.getHistoryOrder(w_id);
    }

    /**
     * 修改工人历史价格
     *
     * @param worker
     * @param request
     * @return
     */
    @PutMapping("/updateprices")
    public Result<Worker> updateHistoryPrices(@RequestBody Worker worker, HttpServletRequest request) {
        Result<Worker> result = new Result<>();
        Integer u_role = JwtUtil.getU_role(request);
        if (u_role != 9) {
            result.setResultFailed("没有权限修改，请联系管理员！");
            return result;
        }
        return workerService.updateWorkerPrices(worker);
    }
}
