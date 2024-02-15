package com.zb.express.front.service.impl;

import com.zb.express.front.mapper.ExpressCompanyMapper;
import com.zb.express.front.service.ExpressCompanyService;
import com.zb.express.pojo.ExpressCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpressCompanyServiceImpl implements ExpressCompanyService {

    @Autowired
    private ExpressCompanyMapper expressCompanyMapper;

    @Override
    public List<ExpressCompany> queryAllExpressCompany() {
        return expressCompanyMapper.selectAllCompany();
    }

    @Override
    public ExpressCompany queryCompanyById(String companyId) {
        return expressCompanyMapper.selectCompanyById(companyId);
    }
}
