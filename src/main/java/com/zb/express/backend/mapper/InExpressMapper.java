package com.zb.express.backend.mapper;


import com.zb.express.pojo.InExpress;

import java.util.List;
import java.util.Map;

public interface InExpressMapper {

    List<Map<String, Object>> selectInExpressPage(Map<String, Object> map);

    int insertInExpress(InExpress inExpress);

    Map<String, Object> selectInExpressById(String id);

    int deleteInExpressById(String id);

    InExpress selectInExpress(String id);

    int updateInExpressStatusById(InExpress inExpress);

    int updateInExpress(InExpress inExpress);

    int selectInExpressCount(String id);

    int selectInExpressStatusById(Integer id);


}
