package com.zb.express.backend.service;

import com.zb.express.commons.entry.PageResult;
import com.zb.express.pojo.Feedback;

import java.util.Map;

public interface FeedbackService {


    PageResult queryFeedbackForPage(Integer pageNo, Integer pageSize,Map<String,Object> map);

    Integer handleFeedback(String id);

    int deleteFeedbackById(String id);

    Map<String, Object> queryFeedbackById(String id);

}
