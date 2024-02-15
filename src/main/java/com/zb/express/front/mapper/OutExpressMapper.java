package com.zb.express.front.mapper;



import com.zb.express.pojo.OutExpress;

import java.util.List;
import java.util.Map;

public interface OutExpressMapper {
    List<Map<String, Object>> selectOutExpressPage(Map<String, Object> map);

    Map<String,Object> selectOutExpressById(String id);

    int insertOutExpress(OutExpress outExpress);

    int deleteOutExpressById(String id);

    OutExpress selectOutExpress(String id);

    int updateOutExpress(OutExpress newOutExpress);
}
