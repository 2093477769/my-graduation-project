package com.zb.express.backend.controller;

import com.zb.express.backend.service.ExpressCompanyService;
import com.zb.express.backend.service.InExpressService;
import com.zb.express.backend.service.OutExpressService;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.entry.Result;
import com.zb.express.pojo.ExpressCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/backend/company")
@RestController
public class ExpressCompanyController {

    @Autowired
    private ExpressCompanyService expressCompanyService;

    @Autowired
    private InExpressService inExpressService;

    @Autowired
    private OutExpressService outExpressService;

    @GetMapping("/getAllExpressCompany")
    public PageResult getAllExpressCompany(Integer pageNo,Integer pageSize){
        PageResult pageResult = expressCompanyService.queryAllExpressCompanyForPage(pageNo,pageSize);
        return pageResult;
    }

    @PostMapping("/addCompany")
    public Result addCompany(ExpressCompany expressCompany){
        ExpressCompany resultCompany=expressCompanyService.queryCompanyByName(expressCompany.getName());
        if (resultCompany!=null){
            return new Result(false,"此快递公司已录入,请修改快递公司名称");
        }
        Integer result=expressCompanyService.addCompany(expressCompany);
        if (result!=1){
            return new Result(false,"添加失败");
        }
        return new Result(true,"添加成功");
    }

    @PostMapping("/deleteCompanyById")
    public Result deleteCompanyByIds(String id){
        int inCount=inExpressService.queryInExpressCountByCIdODId(id);
        int outCount=outExpressService.queryOutExpressCountByCIdOrDId(id);
        if (inCount+outCount!=0){
            return new Result(false,"请先删除使用此公司的快递");
        }
        Integer result= expressCompanyService.deleteCompanyById(id);
        if (result==0){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }

    @PostMapping("/queryCompanyById")
    public Result queryCompanyById(String id){
        ExpressCompany company=expressCompanyService.queryCompanyById(id);
        if (company==null){
            return new Result(false,"系统异常请稍后重试!");
        }
        return new Result(true,null,company);
    }

    @PostMapping("/modifyCompany")
    public Result modifyCompany(ExpressCompany company){
        Integer result=expressCompanyService.modifyCompany(company);
        if (result==0){
            return new Result(false,"修改失败");
        }
        return new Result(true,"修改成功");
    }
}
