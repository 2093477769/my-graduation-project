package com.zb.express.backend.controller;

import com.zb.express.backend.service.OutExpressService;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.entry.Result;
import com.zb.express.pojo.OutExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/backend/outExpress")
@RestController
public class OutExpressController {

    @Autowired
    private OutExpressService outExpressService;


    @GetMapping("/queryOutExpressPage")
    public PageResult queryOutExpressPage(
            Integer pageNo,Integer pageSize,String sender,String receiver, String status){
        Map<String,Object> map=new HashMap<>();
        map.put("sender",sender);
        map.put("receiver",receiver);
        map.put("status",status);
        PageResult result = outExpressService.queryOutExpressPage(pageNo,pageSize,map);
        return result;
    }

    @PostMapping("/takeOutExpress")
    public Result takeOutExpress(OutExpress outExpress){
        int result=outExpressService.modifyOutExpressStatusAndDeliverymanId(outExpress);
        if (result==0){
            return new Result(false,"系统异常请稍后重试");
        }
        return new Result(true,"开始取件");
    }

    ////通过id获取快递信息
    @PostMapping("/getOutExpress")
    public Result getOutExpress(String id){
        OutExpress outExpress=outExpressService.getOutExpressById(id);
        if (outExpress==null){
            return new Result(false,"系统异常请稍后重试");
        }
        return new Result(true,null,outExpress);
    }

    @PostMapping("/modifyOutExpressStatusAndDeliverymanId")
    public Result modifyOutExpressStatusAndDeliverymanId(OutExpress outExpress){
        int result=outExpressService.modifyOutExpressStatusAndDeliverymanId(outExpress);
        if (result==0){
            return new Result(false,"系统异常请稍后重试");
        }
        return new Result(true,"修改成功");
    }

    //通过id获取更详细的快递信息
    @PostMapping("/getOutExpressById")
    public Result getOutExpressById(String id){
        Map<String,Object> map=outExpressService.queryOutExpressById(id);
        if (map==null){
            return new Result(false,"系统异常清稍后重试");
        }
        return new Result(true,null,map);
    }

}
