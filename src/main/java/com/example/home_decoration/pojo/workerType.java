package com.example.home_decoration.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class workerType implements Serializable {
    private String t_jobName;
    private Integer t_number;
}
