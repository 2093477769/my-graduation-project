package com.zb.express.front.controller;

import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.entry.Result;
import com.zb.express.front.service.ExpressCompanyService;
import com.zb.express.front.service.OutExpressService;
import com.zb.express.pojo.ExpressCompany;
import com.zb.express.pojo.OutExpress;
import com.zb.express.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/front/outExpress")
@RestController
public class OutExpressController {

    @Autowired
    private OutExpressService outExpressService;

    @Autowired
    private ExpressCompanyService expressCompanyService;

    @GetMapping("/queryOutExpressForPage")
    public PageResult queryOutExpressPage(
            Integer pageNo, Integer pageSize, String receiver, String status, HttpSession session){
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        Map<String,Object> map=new HashMap<>();
        map.put("receiver",receiver);
        map.put("status",status);
        map.put("sendPhone",user.getPhone());
        PageResult result = outExpressService.queryOutExpressPage(pageNo,pageSize,map);
        return result;
    }

    @PostMapping("/getOutExpressById")
    public Result getOutExpressById(String id){
        Map<String,Object> map=outExpressService.queryOutExpressById(id);
        if (map==null){
            return new Result(false,"系统异常请稍后重试");
        }
        return new Result(true,null,map);
    }

    @PostMapping("/getExpense")
    public Result getExpense(String companyId,Double weight){
        ExpressCompany expressCompany= expressCompanyService.queryCompanyById(companyId);
        BigDecimal baseFee = expressCompany.getBaseFee();
        BigDecimal weightFee = expressCompany.getWeightFee();
        // 计算价格
        BigDecimal totalFee;
        if (weight < 1) {
            totalFee = baseFee;
        } else {
            // 计算超出部分的重量
            BigDecimal excessWeight = BigDecimal.valueOf(weight - 1);
            // 计算需要加的weightfee次数
            int weightFeeMultiples = excessWeight.divide(BigDecimal.valueOf(0.1) ,0, RoundingMode.CEILING).intValue();
            // 计算额外费用
            BigDecimal additionalFee = weightFee.multiply(BigDecimal.valueOf(weightFeeMultiples));
            totalFee = baseFee.add(additionalFee);
        }

        return new Result(true,null,totalFee);
    }

    @PostMapping("/addOutExpress")
    public Result addOutExpress(OutExpress outExpress, MultipartFile file){
        try {
            int result=outExpressService.addOutExpress(outExpress,file);
            if (result==0){
                return new Result(false,"系统异常请稍后重试");
            }
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"系统异常请稍后重试");
        }
    }

    @PostMapping("/deleteOutExpress")
    public Result deleteOutExpressById(String id){
        int result=outExpressService.deleteOutExpressById(id);
        if (result==0){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }

    @PostMapping("/getOutExpress")
    public Result getOutExpress(String id){
        OutExpress outExpress=outExpressService.queryOutExpress(id);
        if (outExpress==null){
            return new Result(false,"系统异常,请稍后重试");
        }
        return new Result(true,null,outExpress);
    }

    @PostMapping("/modifyOutExpress")
    private Result modifyOutExpress(OutExpress newOutExpress,MultipartFile file){
        OutExpress outExpress=outExpressService.queryOutExpress(newOutExpress.getId().toString());
        if (outExpress.getStatus()>1){
            if (outExpress.getCompanyId()!=newOutExpress.getCompanyId()||outExpress.getWeight()!=newOutExpress.getWeight()){
                return new Result(false,"已入库的快递不能修改快递公司或快递重量");
            }
        }
        try {
            int result=outExpressService.modifyOutExpress(newOutExpress,file);
            if (result==0){
                return new Result(false,"修改失败");
            }
            return new Result(true,"修改成功");
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false,"系统异常请稍后重试");
        }

    }

}
