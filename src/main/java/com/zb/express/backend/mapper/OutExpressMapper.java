package com.zb.express.backend.mapper;



import com.zb.express.pojo.OutExpress;

import java.util.List;
import java.util.Map;

public interface OutExpressMapper {
    List<Map<String, Object>> selectOutExpressPage(Map<String, Object> map);

    int selectOutExpressCount(String id);

    int modifyOutExpressStatusAndDeliverymanId(OutExpress outExpress);

    OutExpress selectOutExpressById(String id);

    Map<String, Object> selectOutExpress(String id);
}
