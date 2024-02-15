package com.zb.express.backend.service;

import com.zb.express.commons.entry.PageResult;
import com.zb.express.pojo.ExpressCompany;

import java.util.List;
import java.util.Map;

public interface ExpressCompanyService {


    PageResult queryAllExpressCompanyForPage(Integer pageNo, Integer pageSize);


    Integer addCompany(ExpressCompany expressCompany);

    Integer deleteCompanyById(String id);

    ExpressCompany queryCompanyById(String id);

    Integer modifyCompany(ExpressCompany company);

    ExpressCompany queryCompanyByName(String name);

    List<ExpressCompany> queryAllExpressCompany();
}
