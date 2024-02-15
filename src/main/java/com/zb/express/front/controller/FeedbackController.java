package com.zb.express.front.controller;

import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.entry.Result;
import com.zb.express.front.service.FeedbackService;
import com.zb.express.pojo.Feedback;
import com.zb.express.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RequestMapping("/front/feedback")
@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    @GetMapping("/queryFeedbackForPage")
    public PageResult queryFeedbackForPageToFront(
            Integer pageNo, Integer pageSize, String status, HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("userId",user.getId());
        PageResult result = feedbackService.queryFeedbackForPageToFront(pageNo, pageSize, map);
        return result;
    }

    @PostMapping("/detailFeedback")
    public Result getFeedback(String id){
        Feedback feedback=feedbackService.queryFeedbackByIdToFront(id);
        if (feedback == null){
            return new Result(false,"系统异常,请稍后重试");
        }
        return new Result(true,null,feedback);
    }

    @PostMapping("/modifyFeedback")
    public Result modifyFeedback(Feedback feedback){
        Feedback resultFeedback=feedbackService.queryFeedbackByIdToFront(feedback.getId().toString());
        if (resultFeedback.getStatus()==1){
            return new Result(false,"已经处理的建议/投诉不能修改");
        }
        int result=feedbackService.modifyFeedback(feedback);
        if (result!=1){
            return new Result(false,"修改失败");
        }
        return new Result(true,"修改成功");
    }

    @PostMapping("/addFeedback")
    public Result addFeedback(Feedback feedback,HttpSession session){
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        feedback.setUserId(user.getId());
        int result = feedbackService.addFeedback(feedback);
        if (result!= 1){
            return new Result(false,"发送失败,请稍后重试");
        }
        return new Result(true,"发送成功");
    }

}
