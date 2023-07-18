package com.example.home_decoration.model;

/**
 * 用户排名
 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QueryHome {
    private Integer w_ranking;
    private String w_garde;
    private String w_name;
    private Integer w_age;
    private Integer w_completedQuantity;
    private Integer w_state;
    private Integer w_id;
}
