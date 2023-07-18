package com.example.home_decoration.service.impl;

import com.example.home_decoration.mapper.WorkerOrderMapper;
import com.example.home_decoration.service.WorkerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkerOrderServiceImpl implements WorkerOrderService {

    @Autowired
    WorkerOrderMapper workerOrderMapper;



}
