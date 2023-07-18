package com.example.home_decoration.service.impl;

import com.example.home_decoration.mapper.WorkerTypeMapper;
import com.example.home_decoration.model.Result;
import com.example.home_decoration.service.WorkerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class WorkerTypeImpl implements WorkerType {

    @Autowired
    WorkerTypeMapper workerType;

    @Override
    public Result<List<WorkerType>> getWorkerType() {
        Result<List<WorkerType>> result = new Result<>();
        result.setResultSuccess("1", workerType.getWorkerType());
        return result;
    }
}
