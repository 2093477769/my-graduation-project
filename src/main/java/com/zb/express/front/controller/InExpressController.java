package com.zb.express.front.controller;

import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.entry.Result;
import com.zb.express.front.service.InExpressService;
import com.zb.express.pojo.InExpress;
import com.zb.express.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/front/inExpress")
@RestController
public class InExpressController {

    @Autowired
    private InExpressService inExpressService;

    @GetMapping("/queryInExpressPage")
    public PageResult queryInExpressPageToFront(
            Integer pageNo, Integer pageSize, String sender, String status, HttpSession session){
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        Map<String,Object> map=new HashMap<>();
        map.put("sender",sender);
        map.put("status",status);
        map.put("receiverPhone",user.getPhone());
        PageResult result = inExpressService.queryInExpressPageToFront(pageNo,pageSize,map);
        return result;
    }

    @PostMapping("/modifyInExpressStatus")
    private Result modifyInExpressStatusById(Integer id){
        int status=inExpressService.queryInExpressStatusById(id);
        if (status==0){
            return new Result(false,"快递还未配送");
        }
        InExpress inExpress=new InExpress();
        inExpress.setId(id);
        inExpress.setStatus(2);
        int result=inExpressService.modifyInExpressStatus(inExpress);
        if (result==0){
            return new Result(false,"系统异常请稍后重试");
        }
        return new Result(true,"取件成功");
    }

}
