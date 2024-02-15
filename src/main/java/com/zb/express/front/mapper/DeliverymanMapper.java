package com.zb.express.front.mapper;

import com.zb.express.pojo.Deliveryman;

import java.util.List;
import java.util.Map;

public interface DeliverymanMapper {
    List<Deliveryman> selectDeliverymanByStatus();
}
