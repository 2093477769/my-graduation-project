package com.zb.express.front.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.utils.DateUtils;
import com.zb.express.commons.utils.FileUtils;
import com.zb.express.front.mapper.InExpressMapper;
import com.zb.express.front.service.InExpressService;
import com.zb.express.pojo.InExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class InExpressServiceImpl implements InExpressService {

    @Autowired
    private InExpressMapper inExpressMapper;

    @Override
    public int queryInExpressStatusById(Integer id) {
        return inExpressMapper.selectInExpressStatusById(id);
    }

    @Override
    public int modifyInExpressStatus(InExpress inExpress) {
        return inExpressMapper.updateInExpressStatusById(inExpress);
    }

    @Override
    public PageResult queryInExpressPageToFront(Integer pageNo, Integer pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String,Object>> feedbackList=inExpressMapper.selectInExpressPageToFront(map);
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(feedbackList);
        List<Map<String, Object>> feedbacks = mapPageInfo.getList();
        return new PageResult(mapPageInfo.getTotal(), feedbacks);
    }

}
