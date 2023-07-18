package com.example.home_decoration.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"u_password"}, allowSetters = true)
public class User implements Serializable {
    private Integer u_id;
    @NotEmpty(message = "用户名不能为空! ")
    private String u_username;
    @NotEmpty(message = "密码不能为空! ")
    private String u_password;
    private int u_sex;
    private String u_phone;
    private String u_wechatNumber;
    private int u_role;
    private String u_picture;

}
