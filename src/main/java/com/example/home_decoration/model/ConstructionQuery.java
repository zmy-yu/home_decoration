package com.example.home_decoration.model;
/**
 * 首页工人项目要信息
 */

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ConstructionQuery {
    private String w_name;
    private String o_address;
    private String o_type;
    private LocalDate o_firstDate;
    private LocalDate o_lastDate;
    private Integer o_schedule;
    private Integer o_id;
    private Integer w_id;
}
