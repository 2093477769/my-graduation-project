package com.zb.express.backend.mapper;

import com.zb.express.pojo.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FeedbackMapper {

    List<Map<String,Object>> selectFeedbackForPage(Map<String,Object> map);

    Integer updateFeedbackStatusById(@Param("id") String id, @Param("updateTime") String updateTime);

    int deleteFeedbackById(String id);

    Map<String, Object> selectFeedBackById(String id);

}
