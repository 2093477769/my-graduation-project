package com.zb.express.front.mapper;


import com.zb.express.pojo.InExpress;

import java.util.List;
import java.util.Map;

public interface InExpressMapper {

    int updateInExpressStatusById(InExpress inExpress);

    int selectInExpressStatusById(Integer id);

    List<Map<String, Object>> selectInExpressPageToFront(Map<String, Object> map);
}
