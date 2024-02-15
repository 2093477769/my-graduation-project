package com.zb.express.backend.controller;

import com.zb.express.backend.service.DeliverymanService;
import com.zb.express.backend.service.InExpressService;
import com.zb.express.backend.service.OutExpressService;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.entry.Result;
import com.zb.express.pojo.Deliveryman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RequestMapping("/backend/deliveryman")
@RestController
public class DeliverymanController {

    @Autowired
    private DeliverymanService deliverymanService;

    @Autowired
    private InExpressService inExpressService;

    @Autowired
    private OutExpressService outExpressService;


    @GetMapping("/queryDeliverymanForPage")
    public PageResult queryDeliverymanForPage(
            Integer pageNo,Integer pageSize,String name,String minAge,String maxAge,String gender,String status){
        Map<String,Object> map=new HashMap<>();
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        map.put("name",name);
        map.put("minAge",minAge);
        map.put("maxAge",maxAge);
        map.put("gender",gender);
        map.put("status",status);
       PageResult result = deliverymanService.queryDeliverymanForPage(map);
       return result;
    }

    @PostMapping("/deleteDeliverymanById")
    public Result deleteDeliverymanById(String id){
        int inCount=inExpressService.queryInExpressCountByCIdODId(id);
        int outCount=outExpressService.queryOutExpressCountByCIdOrDId(id);
        if (inCount+outCount!=0){
            return new Result(false,"请先删除此配送员配送或取件的快递信息");
        }
        Integer result= deliverymanService.deleteDeliverymanById(id);
        if (result==0){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }

    @PostMapping("/addDeliveryman")
    public Result addDeliveryman(Deliveryman deliveryman){
        Integer result=deliverymanService.addDeliveryman(deliveryman);
        if (result!=1){
            return new Result(false,"添加失败");
        }
        return new Result(true,"添加成功");
    }

    @PostMapping("/queryDeliverymanById")
    public Result queryDeliverymanById(String id){
        Deliveryman deliveryman=deliverymanService.queryDeliverymanById(id);
        if (deliveryman==null){
            return new Result(false,"系统异常请稍后重试!");
        }
        return new Result(true,null,deliveryman);
    }

    @PostMapping("/modifyDeliveryman")
    public Result modifyDeliveryman(Deliveryman deliveryman){
        System.out.println(deliveryman.getGender());
        System.out.println(deliveryman.getStatus());
        Integer result=deliverymanService.modifyDeliveryman(deliveryman);
        if (result==0){
            return new Result(false,"修改失败");
        }
        return new Result(true,"修改成功");
    }

}
