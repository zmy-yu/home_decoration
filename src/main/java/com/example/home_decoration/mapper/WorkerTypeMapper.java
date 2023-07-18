package com.example.home_decoration.mapper;

import com.example.home_decoration.service.WorkerType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface WorkerTypeMapper {
    /**
     * 获取工种信息
     * @return
     */
    List<WorkerType> getWorkerType();
}
