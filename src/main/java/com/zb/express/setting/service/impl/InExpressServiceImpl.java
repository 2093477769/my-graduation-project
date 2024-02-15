package com.zb.express.setting.service.impl;


import com.zb.express.setting.mapper.InExpressMapper;
import com.zb.express.setting.service.InExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InExpressServiceImpl implements InExpressService {

    @Autowired
    private InExpressMapper inExpressMapper;
    @Override
    public List<String> queryAllInExpressImg() {
        return inExpressMapper.selectAllInExpressImg();
    }
}
