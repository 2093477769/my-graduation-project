package com.zb.express.backend.service;

import com.zb.express.commons.entry.PageResult;
import com.zb.express.pojo.Deliveryman;

import java.util.List;
import java.util.Map;

public interface DeliverymanService {
    PageResult queryDeliverymanForPage(Map<String,Object> map);


    Integer deleteDeliverymanById(String id);

    Integer addDeliveryman(Deliveryman deliveryman);

    Deliveryman queryDeliverymanById(String id);

    Integer modifyDeliveryman(Deliveryman deliveryman);

    List<Deliveryman> queryDeliverymanByStatus();
}
