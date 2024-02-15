package com.zb.express.setting.service.impl;

import com.zb.express.setting.mapper.OutExpressMapper;
import com.zb.express.setting.service.OutExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutExpressServiceImpl implements OutExpressService {

    @Autowired
    private OutExpressMapper outExpressMapper;
    @Override
    public List<String> queryAllOutExpressImg() {
        return outExpressMapper.selectAllOutExpressImg();
    }
}
