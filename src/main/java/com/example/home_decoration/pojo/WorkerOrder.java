package com.example.home_decoration.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WorkerOrder {
    private Integer o_id;
    private String o_number;
    private LocalDate o_date;
    private String o_address;
    private String o_type;
    private LocalDate o_firstdate;
    private LocalDate o_lastdate;
    private String o_schedule;
    private Integer w_id;
    private Integer o_garde;
    private String o_area;
    private Integer o_price;
}
