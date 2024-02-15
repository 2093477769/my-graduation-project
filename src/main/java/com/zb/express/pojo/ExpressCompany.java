package com.zb.express.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpressCompany {
    private Integer id;
    private String name;
    private BigDecimal baseFee;
    private BigDecimal weightFee;
    private String createTime;
    private String updateTime;
}
