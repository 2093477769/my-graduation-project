package com.zb.express.front.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.utils.DateUtils;
import com.zb.express.front.mapper.FeedbackMapper;
import com.zb.express.front.service.FeedbackService;
import com.zb.express.pojo.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public PageResult queryFeedbackForPageToFront(Integer pageNo, Integer pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String,Object>> feedbackList=feedbackMapper.selectFeedbackForPageToFront(map);
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(feedbackList);
        List<Map<String, Object>> feedbacks = mapPageInfo.getList();
        for (Map<String, Object> feedback : feedbacks) {
            String content = (String) feedback.get("content");
            if (content.length()>30){
                content = content.substring(0, 30) + "...";
                feedback.put("content",content);
            }
        }
        return new PageResult(mapPageInfo.getTotal(), feedbacks);
    }

    @Override
    public Feedback queryFeedbackByIdToFront(String id) {
        return feedbackMapper.selectFeedBackByIdToFront(id);
    }

    @Override
    public int modifyFeedback(Feedback feedback) {
        return feedbackMapper.updateFeedback(feedback);
    }

    @Override
    public int addFeedback(Feedback feedback) {
        feedback.setStatus(0);
        feedback.setCreateTime(DateUtils.dateToString(new Date()));
        return feedbackMapper.insertFeedback(feedback);
    }
}
