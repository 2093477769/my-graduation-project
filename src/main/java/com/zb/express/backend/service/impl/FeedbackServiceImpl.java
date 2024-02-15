package com.zb.express.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.express.backend.service.FeedbackService;
import com.zb.express.backend.mapper.FeedbackMapper;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.utils.DateUtils;
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
    public PageResult queryFeedbackForPage(Integer pageNo, Integer pageSize, Map<String,Object> map) {
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String,Object>> feedbackList=feedbackMapper.selectFeedbackForPage(map);
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
    public Integer handleFeedback(String id) {
        String updateTime = DateUtils.dateToString(new Date());
        return feedbackMapper.updateFeedbackStatusById(id,updateTime);
    }

    @Override
    public int deleteFeedbackById(String id) {
        return feedbackMapper.deleteFeedbackById(id);
    }

    @Override
    public Map<String, Object> queryFeedbackById(String id) {
        return feedbackMapper.selectFeedBackById(id);
    }

}
