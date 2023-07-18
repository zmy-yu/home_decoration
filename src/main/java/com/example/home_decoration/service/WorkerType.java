package com.example.home_decoration.service;

import com.example.home_decoration.model.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkerType {
    public Result<List<WorkerType>> getWorkerType();
}
