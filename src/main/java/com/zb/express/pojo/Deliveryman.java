package com.zb.express.pojo;

import lombok.Data;


@Data
public class Deliveryman {
    private Integer id;
    private String name;
    private Integer gender;
    private Integer age;
    private String phone;
    private Integer status;
    private String createTime;
    private String updateTime;
}
