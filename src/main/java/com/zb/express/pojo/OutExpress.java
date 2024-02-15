package com.zb.express.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OutExpress {
    private Integer id;
    private Integer companyId;
    private String img;
    private Double weight;
    private Integer status;
    private Integer deliverymanId;
    private BigDecimal expense;
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String createTime;
    private String updateTime;

}
