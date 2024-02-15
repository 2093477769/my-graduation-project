package com.zb.express.front.service;

import com.zb.express.commons.entry.PageResult;
import com.zb.express.pojo.Feedback;

import java.util.Map;

public interface FeedbackService {


    PageResult queryFeedbackForPageToFront(Integer pageNo, Integer pageSize, Map<String, Object> map);


    Feedback queryFeedbackByIdToFront(String id);

    int modifyFeedback(Feedback feedback);

    int addFeedback(Feedback feedback);
}
