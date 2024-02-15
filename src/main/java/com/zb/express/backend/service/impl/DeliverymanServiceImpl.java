package com.zb.express.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.express.backend.mapper.DeliverymanMapper;
import com.zb.express.backend.service.DeliverymanService;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.utils.DateUtils;
import com.zb.express.pojo.Deliveryman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DeliverymanServiceImpl implements DeliverymanService {

    @Autowired
    private DeliverymanMapper deliverymanMapper;

    @Override
    public PageResult queryDeliverymanForPage(Map<String,Object> map) {
        PageHelper.startPage((Integer) map.get("pageNo"),(Integer) map.get("pageSize"));
        List<Deliveryman> deliverymanList=deliverymanMapper.selectDeliverymanForPage(map);;
        PageInfo<Deliveryman> mapPageInfo = new PageInfo<>(deliverymanList);
        return new PageResult(mapPageInfo.getTotal(), mapPageInfo.getList());
    }


    @Override
    public Integer deleteDeliverymanById(String id) {
        return deliverymanMapper.deleteDeliverymanById(id);
    }

    @Override
    public Integer addDeliveryman(Deliveryman deliveryman) {
        deliveryman.setCreateTime(DateUtils.dateToString(new Date()));
        return deliverymanMapper.insertDeliveryman(deliveryman);
    }

    @Override
    public Deliveryman queryDeliverymanById(String id) {
        return deliverymanMapper.selectDeliverymanById(id);
    }

    @Override
    public Integer modifyDeliveryman(Deliveryman deliveryman) {
        deliveryman.setUpdateTime(DateUtils.dateToString(new Date()));
        return deliverymanMapper.updateDeliveryman(deliveryman);
    }

    @Override
    public List<Deliveryman> queryDeliverymanByStatus() {
        return deliverymanMapper.selectDeliverymanByStatus();
    }
}
