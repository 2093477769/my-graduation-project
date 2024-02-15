package com.zb.express.backend.mapper;

import com.zb.express.pojo.Deliveryman;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeliverymanMapper {

    List<Deliveryman> selectDeliverymanForPage(Map<String, Object> map);

    Integer deleteDeliverymanById(String id);

    Integer insertDeliveryman(Deliveryman deliveryman);

    Deliveryman selectDeliverymanById(String id);

    Integer updateDeliveryman(Deliveryman deliveryman);

    List<Deliveryman> selectDeliverymanByStatus();
}
