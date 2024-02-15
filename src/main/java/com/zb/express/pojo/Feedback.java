package com.zb.express.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Feedback {
    private Integer id;
    private Integer userId;
    private String content;
    private Integer status;
    private String createTime;
    private String updateTime;
}
