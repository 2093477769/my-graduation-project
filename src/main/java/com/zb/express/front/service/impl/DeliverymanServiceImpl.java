package com.zb.express.front.service.impl;

import com.zb.express.front.mapper.DeliverymanMapper;
import com.zb.express.front.service.DeliverymanService;
import com.zb.express.pojo.Deliveryman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverymanServiceImpl implements DeliverymanService {

    @Autowired
    private DeliverymanMapper deliverymanMapper;

    @Override
    public List<Deliveryman> queryDeliverymanByStatus() {
        return deliverymanMapper.selectDeliverymanByStatus();
    }
}
