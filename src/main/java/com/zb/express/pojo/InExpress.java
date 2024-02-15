package com.zb.express.pojo;

import lombok.Data;

@Data
public class InExpress {
    private Integer id;
    private Integer companyId;
    private String img;
    private Integer status;
    private Integer deliverymanId;
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private String receiverName;
    private String receiverPhone;
    private String receiverAddress;
    private String createTime;
    private String updateTime;

}
