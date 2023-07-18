package com.example.home_decoration.contorller;

import com.example.home_decoration.model.Result;
import com.example.home_decoration.service.WorkerType;
import com.example.home_decoration.service.impl.WorkerTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workerType")
public class WorkerTypeController {
    @Autowired
    WorkerTypeImpl workerType;

    @GetMapping("")
    public Result<List<WorkerType>> getWorkerType() {
        return workerType.getWorkerType();
    }
}
