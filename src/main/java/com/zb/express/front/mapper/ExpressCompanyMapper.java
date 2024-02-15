package com.zb.express.front.mapper;

import com.zb.express.pojo.ExpressCompany;

import java.util.List;

public interface ExpressCompanyMapper {

    List<ExpressCompany> selectAllCompany();

    ExpressCompany selectCompanyById(String companyId);
}
