package com.zb.express.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.express.backend.mapper.ExpressCompanyMapper;
import com.zb.express.backend.service.ExpressCompanyService;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.utils.DateUtils;
import com.zb.express.pojo.ExpressCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpressCompanyServiceImpl implements ExpressCompanyService {

    @Autowired
    private ExpressCompanyMapper expressCompanyMapper;

    @Override
    public PageResult queryAllExpressCompanyForPage(Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<ExpressCompany> expressCompanyList=expressCompanyMapper.selectAllCompany();
        PageInfo<ExpressCompany> mapPageInfo = new PageInfo<>(expressCompanyList);
        return new PageResult(mapPageInfo.getTotal(), mapPageInfo.getList());
    }

    @Override
    public Integer addCompany(ExpressCompany expressCompany) {
        expressCompany.setCreateTime(DateUtils.dateToString(new Date()));
        return expressCompanyMapper.insertCompany(expressCompany);

    }

    @Override
    public Integer deleteCompanyById(String id) {
        return expressCompanyMapper.deleteCompanyById(id);
    }

    @Override
    public ExpressCompany queryCompanyById(String id) {
        return expressCompanyMapper.selectCompanyById(id);
    }

    @Override
    public Integer modifyCompany(ExpressCompany company) {
        company.setUpdateTime(DateUtils.dateToString(new Date()));
        return expressCompanyMapper.upadteCompany(company);
    }

    @Override
    public ExpressCompany queryCompanyByName(String name) {
        return expressCompanyMapper.selectCompanyByName(name);
    }

    @Override
    public List<ExpressCompany> queryAllExpressCompany() {
        return expressCompanyMapper.selectAllCompany();
    }
}
