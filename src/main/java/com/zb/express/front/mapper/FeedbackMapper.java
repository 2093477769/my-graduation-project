package com.zb.express.front.mapper;

import com.zb.express.pojo.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FeedbackMapper {


    List<Map<String, Object>> selectFeedbackForPageToFront(Map<String, Object> map);

    Feedback selectFeedBackByIdToFront(String id);

    int updateFeedback(Feedback feedback);

    int insertFeedback(Feedback feedback);
}
