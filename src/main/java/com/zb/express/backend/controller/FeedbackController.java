package com.zb.express.backend.controller;

import com.zb.express.backend.service.FeedbackService;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.entry.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RequestMapping("/backend/feedback")
@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/queryFeedbackForPage")
    public PageResult queryFeedbackForPage(
            Integer pageNo, Integer pageSize, String username, String status) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("status", status);
        PageResult result = feedbackService.queryFeedbackForPage(pageNo, pageSize, map);
        return result;
    }

    @PostMapping("/handleFeedback")
    public Result handleFeedback(String id) {
        int result = 0;
        result = feedbackService.handleFeedback(id);
        if (result == 0) {
            return new Result(false, "系统异常,请稍后重试");
        }
        return new Result(true, "处理成功");
    }

    @PostMapping("/deleteFeedback")
    public Result deleteFeedback(String id) {
        int result = 0;
        result = feedbackService.deleteFeedbackById(id);
        if (result == 0) {
            return new Result(false, "系统异常,请稍后重试");
        }
        return new Result(true, "删除成功");
    }

    @PostMapping("/detailFeedback")
    public Result detailFeedback(String id) {
        Map<String, Object> map = feedbackService.queryFeedbackById(id);
        if (map.isEmpty()) {
            return new Result(false, "系统异常,请稍后重试");
        }
        return new Result(true, null, map);
    }


}
