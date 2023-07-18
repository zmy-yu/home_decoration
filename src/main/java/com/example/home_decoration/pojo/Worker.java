package com.example.home_decoration.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"w_password"}, allowSetters = true)
public class Worker implements Serializable {
    private Integer w_id;
    private String w_name;
    private String w_idType;
    private String w_idNumber;
    private String w_typeWork;
    private String w_birthday;
    private Integer w_sex;
    private String w_nationality;
    private String w_domicileAddressCity;
    private String w_domicileAddress;
    private String w_phone;
    private String w_habitualResidenceCity;
    private String w_habitualResidence;
    private String w_emergencyContact;
    private String w_nation;
    private String w_wechatNumber;
    private Integer u_id;
    private Integer w_completedQuantity;
    private Integer w_seniority;
    private Integer w_garde;
    private Integer w_price;
    private Integer w_historyPrice;
    private Integer w_ranking;
    private Integer w_age;
    private Integer w_state;
    private String w_picture;
}
