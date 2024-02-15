package com.zb.express.front.service;

import com.zb.express.pojo.ExpressCompany;

import java.util.List;

public interface ExpressCompanyService {

    List<ExpressCompany> queryAllExpressCompany();

    ExpressCompany queryCompanyById(String companyId);
}
