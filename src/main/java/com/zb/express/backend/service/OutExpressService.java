package com.zb.express.backend.service;

import com.zb.express.commons.entry.PageResult;
import com.zb.express.pojo.OutExpress;

import java.util.Map;

public interface OutExpressService {

    //分页查询
    PageResult queryOutExpressPage(Integer pageNo, Integer pageSize, Map<String, Object> map);

    //获取快递公司或者快递员对应的快递数量
    int queryOutExpressCountByCIdOrDId(String id);

    //配送快递修改快递状态及对应快递员
    int modifyOutExpressStatusAndDeliverymanId(OutExpress outExpress);

    //通过id获取快递信息
    OutExpress getOutExpressById(String id);

    Map<String, Object> queryOutExpressById(String id);
}
