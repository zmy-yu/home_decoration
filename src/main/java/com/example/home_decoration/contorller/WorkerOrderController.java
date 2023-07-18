package com.example.home_decoration.contorller;

import com.example.home_decoration.service.impl.WorkerOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkerOrderController {

    @Autowired
    WorkerOrderServiceImpl workerOrderService;


}
