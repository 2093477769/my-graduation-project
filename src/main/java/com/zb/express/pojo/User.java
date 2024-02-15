package com.zb.express.pojo;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String realname;
    private String phone;
    private String address;
    private Integer type;
    private String avatar;
    private String createTime;
    private String updateTime;


}
