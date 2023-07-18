package com.example.home_decoration.model;

/**
 * 用户查询
 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkerQuery {
    private String w_typeWork;
    private String w_habitualResidenceCity;
    private Integer pageNum;
    private Integer pageSize;
}
