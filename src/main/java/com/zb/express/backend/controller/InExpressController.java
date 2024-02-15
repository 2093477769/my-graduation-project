package com.zb.express.backend.controller;

import com.zb.express.backend.service.InExpressService;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.entry.Result;
import com.zb.express.pojo.InExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/backend/inExpress")
@RestController
public class InExpressController {

    @Autowired
    private InExpressService inExpressService;


    @GetMapping("/queryInExpressPage")
    public PageResult queryInExpressPageToBackend(
            Integer pageNo,Integer pageSize,String sender,String receiver, String status){
        Map<String,Object> map=new HashMap<>();
        map.put("sender",sender);
        map.put("receiver",receiver);
        map.put("status",status);
        PageResult result = inExpressService.queryInExpressPage(pageNo,pageSize,map);
        return result;
    }

    @PostMapping("/addInExpress")
    public Result addInExpress(InExpress inExpress, MultipartFile file){
        try {
            int result=inExpressService.addInExpress(inExpress,file);
            if (result==0){
                return new Result(false,"系统异常请稍后重试");
            }
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"系统异常请稍后重试");
        }
    }

    @PostMapping("/detailInExpress")
    public Result queryInExpressById(String id){
       Map<String,Object> map= inExpressService.queryInExpressById(id);
       if (map.isEmpty()){
           return new Result(false,"系统异常请稍后重试");
       }
       return new Result(true,null,map);
    }

    @PostMapping("/deleteInExpress")
    public Result deleteInExpressById(String id){
        int result=inExpressService.deleteInExpressById(id);
        if (result==0){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }

    @PostMapping("/sendInExpress")
    public Result sendInExpress(InExpress inExpress){
        int result=inExpressService.sendInExpress(inExpress);
        if (result==0){
            return new Result(false,"系统异常请稍后重试");
        }
        return new Result(true,"开始配送");
    }

    @PostMapping("/getInExpress")
    public Result getInExpressById(String id){
        InExpress inExpress= inExpressService.getInExpressById(id);
        if (inExpress==null){
            return new Result(false,"系统异常请稍后重试");
        }
        return new Result(true,null,inExpress);
    }

    @PostMapping("/modifyInExpress")
    public Result modifyInExpress(InExpress inExpress, MultipartFile file){
        try {
            int result=inExpressService.modifyInExpress(inExpress,file);
            if (result==0){
                return new Result(false,"修改失败");
            }
            return new Result(true,"修改成功");
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,"系统异常请稍后重试");
        }
    }

    @PostMapping("/modifyInExpressStatus")
    private Result modifyInExpressStatus(InExpress inExpress){
        int status=inExpressService.queryInExpressStatusById(inExpress.getId());
        if (status==2){
            return new Result(false,"快递已取件,不能修改");
        }
        int result=inExpressService.modifyInExpressStatus(inExpress);
        if (result==0){
            return new Result(false,"修改失败");
        }
        return new Result(true,"修改成功");
    }


}
