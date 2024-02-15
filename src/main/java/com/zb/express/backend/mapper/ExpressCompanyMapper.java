package com.zb.express.backend.mapper;

import com.zb.express.pojo.ExpressCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExpressCompanyMapper {

    List<ExpressCompany> selectAllCompany();


    Integer insertCompany(ExpressCompany expressCompany);

    Integer deleteCompanyById(String id);

    ExpressCompany selectCompanyById(String id);

    Integer upadteCompany(ExpressCompany company);

    ExpressCompany selectCompanyByName(String name);
}
